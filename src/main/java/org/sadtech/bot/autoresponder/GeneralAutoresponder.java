package org.sadtech.bot.autoresponder;

import org.sadtech.autoresponder.Autoresponder;
import org.sadtech.autoresponder.entity.Unit;
import org.sadtech.autoresponder.service.UnitPointerServiceImpl;
import org.sadtech.bot.autoresponder.domain.unit.MainUnit;
import org.sadtech.bot.autoresponder.domain.unit.TypeUnit;
import org.sadtech.bot.autoresponder.domain.unit.UnitActiveStatus;
import org.sadtech.bot.autoresponder.service.action.*;
import org.sadtech.bot.autoresponder.service.timer.TimerService;
import org.sadtech.bot.core.domain.content.Content;
import org.sadtech.bot.core.service.AccountService;
import org.sadtech.bot.core.service.ContentService;
import org.sadtech.bot.core.service.Filter;
import org.sadtech.bot.core.service.sender.Sent;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.function.Consumer;

public class GeneralAutoresponder<T extends Content> implements Runnable {

    private final ContentService<T> contentService;
    protected final Autoresponder autoresponder;
    protected Map<TypeUnit, ActionUnit> actionUnitMap = new EnumMap<>(TypeUnit.class);
    protected List<Filter<T>> filters;

    protected GeneralAutoresponder(Set<Unit> menuUnit, Sent sent, ContentService<T> contentService) {
        this.contentService = contentService;
        autoresponder = new Autoresponder(new UnitPointerServiceImpl(), menuUnit);
        init(sent);
    }

    public void setFilters(List<Filter<T>> filters) {
        this.filters = filters;
    }

    private MainUnit getAction(T event, MainUnit unitAnswer) {
        return actionUnitMap.get(unitAnswer.getTypeUnit()).action(unitAnswer, event);
    }

    protected void addActionUnit(TypeUnit typeUnit, ActionUnit actionUnit) {
        actionUnitMap.put(typeUnit, actionUnit);
    }

    public void setDefaultUnit(MainUnit defaultUnit) {
        autoresponder.setDefaultUnit(defaultUnit);
    }

    private void init(Sent sent) {
        actionUnitMap.put(TypeUnit.CHECK, new AnswerCheckAction());
        actionUnitMap.put(TypeUnit.PROCESSING, new AnswerProcessingAction(sent));
        actionUnitMap.put(TypeUnit.TEXT, new AnswerTextAction(sent));
        actionUnitMap.put(TypeUnit.SAVE, new AnswerSaveAction());
        actionUnitMap.put(TypeUnit.VALIDITY, new AnswerValidityAction());
        actionUnitMap.put(TypeUnit.NEXT, new AnswerNextAction(autoresponder));
    }

    public void initTimerAction(TimerService timerService) {
        actionUnitMap.put(TypeUnit.TIMER, new AnswerTimerAction(timerService, this));
    }

    public void initAccountAction(AccountService accountService, TimerService timerService) {
        actionUnitMap.put(TypeUnit.ACCOUNT, new AnswerAccountAction(accountService, timerService));
    }

    private void checkNewMessages() {
        LocalDateTime oldData = LocalDateTime.now(Clock.tickSeconds(ZoneId.systemDefault()));
        LocalDateTime newData;
        while (true) {
            newData = LocalDateTime.now(Clock.tickSeconds(ZoneId.systemDefault())).minusSeconds(1);
            if (oldData.isBefore(newData)) {
                contentService.getLastEventByTime(oldData, newData)
                        .parallelStream().forEach(processing());
            }
            oldData = newData;
        }
    }

    private Consumer<T> processing() {
        return event -> {
            if (filters != null) {
                filters.forEach(filter -> filter.processing(event));
            }
            MainUnit unitAnswer = (MainUnit) autoresponder.answer(event.getPersonId(), event.getMessage());
            answer(event, unitAnswer);
        };
    }

    public void answer(T event, MainUnit unitAnswer) {
        MainUnit newUnitAnswer = getAction(event, unitAnswer);
        while (!newUnitAnswer.equals(unitAnswer)) {
            unitAnswer = newUnitAnswer;
            newUnitAnswer = getAction(event, unitAnswer);
        }
        unitAnswer = activeUnitAfter(unitAnswer, event);
        autoresponder.getUnitPointerService().edit(event.getPersonId(), unitAnswer);
    }

    protected MainUnit activeUnitAfter(MainUnit mainUnit, T content) {
        if (mainUnit.getNextUnits() != null) {
            Optional<MainUnit> first = mainUnit.getNextUnits().stream()
                    .filter(unit -> unit instanceof MainUnit)
                    .map(unit -> (MainUnit) unit)
                    .filter(unit -> UnitActiveStatus.AFTER.equals(unit.getActiveStatus()))
                    .findFirst();
            if (first.isPresent()) {
                getAction(content, first.get());
                return activeUnitAfter(first.get(), content);
            }
        }
        return mainUnit;
    }

    @Override
    public void run() {
        checkNewMessages();
    }

}

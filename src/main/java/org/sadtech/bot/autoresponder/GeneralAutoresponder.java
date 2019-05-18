package org.sadtech.bot.autoresponder;

import org.omg.CORBA.MARSHAL;
import org.sadtech.autoresponder.Autoresponder;
import org.sadtech.autoresponder.entity.Unit;
import org.sadtech.autoresponder.service.UnitPointerServiceImpl;
import org.sadtech.bot.autoresponder.domain.unit.MainUnit;
import org.sadtech.bot.autoresponder.domain.unit.TypeUnit;
import org.sadtech.bot.autoresponder.domain.unit.UnitActiveStatus;
import org.sadtech.bot.autoresponder.service.action.*;
import org.sadtech.bot.autoresponder.timer.impl.TimerActionRepositoryList;
import org.sadtech.bot.autoresponder.timer.impl.TimerActionServiceImpl;
import org.sadtech.bot.core.domain.Content;
import org.sadtech.bot.core.filter.Filter;
import org.sadtech.bot.core.sender.Sent;
import org.sadtech.bot.core.service.EventService;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GeneralAutoresponder<T extends Content> implements Runnable {

    private final EventService<T> eventService;
    protected final Autoresponder autoresponder;
    protected final Sent sent;
    protected Map<TypeUnit, ActionUnit> actionUnitMap;
    protected List<Filter> filters;

    protected GeneralAutoresponder(Set<Unit> menuUnit, Sent sent, EventService<T> eventService) {
        this.eventService = eventService;
        this.sent = sent;
        autoresponder = new Autoresponder(new UnitPointerServiceImpl(), menuUnit);
        init(sent);
    }

    public void setFilters(List<Filter> filters) {
        this.filters = filters;
    }

    private void init(Sent sent) {
        actionUnitMap = new EnumMap<>(TypeUnit.class);
        actionUnitMap.put(TypeUnit.CHECK, new AnswerCheckAction());
        actionUnitMap.put(TypeUnit.PROCESSING, new AnswerProcessingAction(sent));
        actionUnitMap.put(TypeUnit.SAVE, new AnswerSaveAction());
        actionUnitMap.put(TypeUnit.TEXT, new AnswerTextAction(sent));
        actionUnitMap.put(TypeUnit.TIMER, new AnswerTimerAction(new TimerActionServiceImpl(new TimerActionRepositoryList()), actionUnitMap));
        actionUnitMap.put(TypeUnit.VALIDITY, new AnswerValidityAction());
        actionUnitMap.put(TypeUnit.HIDDEN_SAVE, new AnswerHiddenSaveAction());
        actionUnitMap.put(TypeUnit.NEXT, new AnswerNextAction(autoresponder, actionUnitMap, autoresponder.getUnitPointerService()));
    }

    private void checkNewMessages() {
        LocalDateTime oldData = LocalDateTime.now(Clock.tickSeconds(ZoneId.systemDefault()));
        LocalDateTime newData;
        while (true) {
            newData = LocalDateTime.now(Clock.tickSeconds(ZoneId.systemDefault())).minusSeconds(1);
            if (oldData.isBefore(newData)) {
                List<T> events = eventService.getFirstEventByTime(oldData, newData);
                events.parallelStream().forEach(event -> {
                    filters.forEach(filter -> filter.doFilter(event));
                    MainUnit unitAnswer = (MainUnit) autoresponder.answer(event.getPersonId(), event.getMessage());
                    MainUnit newUnitAnswer = actionUnitMap.get(unitAnswer.getTypeUnit()).action(unitAnswer, event);
                    while (!newUnitAnswer.equals(unitAnswer)) {
                        unitAnswer = newUnitAnswer;
                        newUnitAnswer = actionUnitMap.get(unitAnswer.getTypeUnit()).action(unitAnswer, event);
                    }
                    autoresponder.getUnitPointerService().edit(event.getPersonId(), unitAnswer);
                    activeUnitAfter(unitAnswer, event);
                });
            }
            oldData = newData;
        }
    }

    protected void activeUnitAfter(MainUnit mainUnit, T content) {
        if (mainUnit.getNextUnits() != null) {
            mainUnit.getNextUnits().stream()
                    .filter(unit -> unit instanceof MainUnit)
                    .map(unit -> (MainUnit) unit)
                    .forEach(nextUnit -> {
                        if (UnitActiveStatus.AFTER.equals(nextUnit.getActiveStatus())) {
                            actionUnitMap.get(nextUnit.getTypeUnit()).action(nextUnit, content);
                            autoresponder.getUnitPointerService().getByEntityId(content.getPersonId()).setUnit(nextUnit);
                            activeUnitAfter(nextUnit, content);
                        }
                    });
        }
    }

    protected void addActionUnit(TypeUnit typeUnit, ActionUnit actionUnit) {
        actionUnitMap.put(typeUnit, actionUnit);
    }

    public void setDefaultUnit(MainUnit defaultUnit) {
        autoresponder.setDefaultUnit(defaultUnit);
    }

    @Override
    public void run() {
        checkNewMessages();
    }

}

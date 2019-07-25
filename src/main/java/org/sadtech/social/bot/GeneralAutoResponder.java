package org.sadtech.social.bot;

import org.sadtech.autoresponder.AutoResponder;
import org.sadtech.autoresponder.entity.Unit;
import org.sadtech.autoresponder.repository.UnitPointerRepositoryMap;
import org.sadtech.autoresponder.service.UnitPointerServiceImpl;
import org.sadtech.social.bot.domain.unit.MainUnit;
import org.sadtech.social.bot.domain.unit.TypeUnit;
import org.sadtech.social.bot.domain.unit.UnitActiveType;
import org.sadtech.social.bot.service.action.ActionUnit;
import org.sadtech.social.bot.service.action.AnswerAccountAction;
import org.sadtech.social.bot.service.action.AnswerCheckAction;
import org.sadtech.social.bot.service.action.AnswerProcessingAction;
import org.sadtech.social.bot.service.action.AnswerSaveAction;
import org.sadtech.social.bot.service.action.AnswerTextAction;
import org.sadtech.social.bot.service.action.AnswerTimerAction;
import org.sadtech.social.bot.service.action.AnswerValidityAction;
import org.sadtech.social.bot.service.timer.TimerService;
import org.sadtech.social.core.domain.content.Message;
import org.sadtech.social.core.service.AccountService;
import org.sadtech.social.core.service.MessageService;
import org.sadtech.social.core.service.Modifiable;
import org.sadtech.social.core.service.sender.Sent;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;

public class GeneralAutoResponder<T extends Message> implements Runnable {

    private final MessageService<T> messageService;
    protected final AutoResponder autoResponder;
    protected Map<TypeUnit, ActionUnit> actionUnitMap = new EnumMap<>(TypeUnit.class);
    protected List<Modifiable<T>> modifiables;

    protected GeneralAutoResponder(Set<Unit> menuUnit, Sent sent, MessageService<T> messageService) {
        this.messageService = messageService;
        autoResponder = new AutoResponder(new UnitPointerServiceImpl(new UnitPointerRepositoryMap()), menuUnit);
        init(sent);
    }

    public void setModifiables(List<Modifiable<T>> modifiables) {
        this.modifiables = modifiables;
    }

    protected void addActionUnit(TypeUnit typeUnit, ActionUnit actionUnit) {
        actionUnitMap.put(typeUnit, actionUnit);
    }

    public void setDefaultUnit(MainUnit defaultUnit) {
        autoResponder.setDefaultUnit(defaultUnit);
    }

    private void init(Sent sent) {
        actionUnitMap.put(TypeUnit.CHECK, new AnswerCheckAction());
        actionUnitMap.put(TypeUnit.PROCESSING, new AnswerProcessingAction(sent));
        actionUnitMap.put(TypeUnit.TEXT, new AnswerTextAction(sent));
        actionUnitMap.put(TypeUnit.SAVE, new AnswerSaveAction());
        actionUnitMap.put(TypeUnit.VALIDITY, new AnswerValidityAction());
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
                messageService.getLastEventByTime(oldData, newData)
                        .parallelStream().forEach(processing());
            }
            oldData = newData;
        }
    }

    private Consumer<T> processing() {
        return event -> {
            if (modifiables != null) {
                modifiables.forEach(modifiable -> modifiable.change(event));
            }
            MainUnit unitAnswer = (MainUnit) autoResponder.answer(event.getPersonId(), event.getText());
            answer(event, unitAnswer);
        };
    }

    public void answer(T event, MainUnit unitAnswer) {
        unitAnswer = getAction(event, unitAnswer);
        unitAnswer = activeUnitAfter(unitAnswer, event);
        if (!(autoResponder.getDefaultUnit() != null && autoResponder.getDefaultUnit().equals(unitAnswer))) {
            autoResponder.getUnitPointerService().edit(event.getPersonId(), unitAnswer);
        }
    }

    private MainUnit getAction(T event, MainUnit unitAnswer) {
        MainUnit mainUnit = actionUnitMap.get(unitAnswer.getTypeUnit()).action(unitAnswer, event);
        while (!unitAnswer.equals(mainUnit)) {
            return getAction(event, mainUnit);
        }
        return mainUnit;
    }

    private MainUnit activeUnitAfter(MainUnit mainUnit, T content) {
        if (mainUnit.getNextUnits() != null) {
            Optional<MainUnit> first = mainUnit.getNextUnits().stream()
                    .filter(unit -> unit instanceof MainUnit)
                    .map(unit -> (MainUnit) unit)
                    .filter(unit -> UnitActiveType.AFTER.equals(unit.getActiveType()))
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

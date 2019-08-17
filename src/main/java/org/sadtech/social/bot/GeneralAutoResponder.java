package org.sadtech.social.bot;

import org.sadtech.autoresponder.AutoResponder;
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
import org.sadtech.social.core.service.sender.Sending;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TimerTask;
import java.util.function.Consumer;

public class GeneralAutoResponder<T extends Message> extends TimerTask {

    private final MessageService<T> messageService;
    protected final AutoResponder<MainUnit> autoResponder;
    protected Map<TypeUnit, ActionUnit<? extends MainUnit, ? extends Message>> actionUnitMap = new EnumMap<>(TypeUnit.class);
    protected List<Modifiable<T>> modifiables;

    protected GeneralAutoResponder(Set<MainUnit> menuUnit,
                                   Sending sending,
                                   MessageService<T> messageService) {
        this.messageService = messageService;
        autoResponder = new AutoResponder<>(new UnitPointerServiceImpl(new UnitPointerRepositoryMap()), menuUnit);
        init(sending);
    }

    public void setModifiables(List<Modifiable<T>> modifiables) {
        this.modifiables = modifiables;
    }

    protected void addActionUnit(TypeUnit typeUnit, ActionUnit<? super MainUnit, T> actionUnit) {
        actionUnitMap.put(typeUnit, actionUnit);
    }

    public void setDefaultUnit(MainUnit defaultUnit) {
        autoResponder.setDefaultUnit(defaultUnit);
    }

    private void init(Sending sending) {
        actionUnitMap.put(TypeUnit.CHECK, new AnswerCheckAction());
        actionUnitMap.put(TypeUnit.PROCESSING, new AnswerProcessingAction(sending));
        actionUnitMap.put(TypeUnit.TEXT, new AnswerTextAction(sending));
        actionUnitMap.put(TypeUnit.VALIDITY, new AnswerValidityAction());
    }

    public void initSaveAction(AnswerSaveAction<?> answerSaveAction) {
        actionUnitMap.put(TypeUnit.SAVE, answerSaveAction);
    }

    public void initTimerAction(TimerService timerService) {
        actionUnitMap.put(TypeUnit.TIMER, new AnswerTimerAction(timerService, this));
    }

    public void initAccountAction(AccountService accountService, TimerService timerService) {
        actionUnitMap.put(TypeUnit.ACCOUNT, new AnswerAccountAction(accountService, timerService));
    }

    private Consumer<T> processing() {
        return event -> {
            if (modifiables != null) {
                modifiables.forEach(modifiable -> modifiable.change(event));
            }
            MainUnit unitAnswer = autoResponder.answer(event.getPersonId(), event.getText());
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
        ActionUnit actionUnit = actionUnitMap.get(unitAnswer.getTypeUnit());
        MainUnit mainUnit = actionUnit.action(unitAnswer, event);
        while (!unitAnswer.equals(mainUnit)) {
            return getAction(event, mainUnit);
        }
        return mainUnit;
    }

    private MainUnit activeUnitAfter(MainUnit mainUnit, T content) {
        if (mainUnit.getNextUnits() != null) {
            Optional<MainUnit> first = mainUnit.getNextUnits().stream()
                    .filter(unit -> UnitActiveType.AFTER.equals(unit.getActiveType()))
                    .findFirst();
            if (first.isPresent()) {
                getAction(content, first.get());
                return activeUnitAfter(first.get(), content);
            }
        }
        return mainUnit;
    }

    @Scheduled(fixedRate = 3000)
    public void checkNewMessage() {
        LocalDateTime newData = LocalDateTime.now(Clock.tickSeconds(ZoneId.systemDefault()));
        List<T> eventByTime = messageService.getNewMessage(newData.plusNanos(999999999));
        if (eventByTime != null && !eventByTime.isEmpty()) {
            new Thread(
                    () -> eventByTime.parallelStream().forEach(processing())
            ).start();
        }
    }

    @Override
    public void run() {
        checkNewMessage();
    }
}

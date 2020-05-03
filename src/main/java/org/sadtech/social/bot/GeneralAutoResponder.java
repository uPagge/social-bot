package org.sadtech.social.bot;

import org.sadtech.autoresponder.AutoResponder;
import org.sadtech.autoresponder.entity.UnitPointer;
import org.sadtech.autoresponder.repository.UnitPointerRepository;
import org.sadtech.autoresponder.service.UnitPointerServiceImpl;
import org.sadtech.social.bot.domain.unit.MainUnit;
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
import org.sadtech.social.bot.utils.TypeUnit;
import org.sadtech.social.core.domain.content.Message;
import org.sadtech.social.core.exception.ConfigAppException;
import org.sadtech.social.core.exception.NotFoundException;
import org.sadtech.social.core.service.AccountService;
import org.sadtech.social.core.service.MessageService;
import org.sadtech.social.core.service.Modifiable;
import org.sadtech.social.core.service.sender.Sending;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

public class GeneralAutoResponder<T extends Message> extends TimerTask {

    private final MessageService<T> messageService;
    protected final AutoResponder<MainUnit> autoResponder;
    protected Map<String, ActionUnit<? extends MainUnit, ? extends Message>> actionUnitMap = new HashMap<>();
    protected List<Modifiable<T>> modifiables;
    private ExecutorService executorService = Executors.newFixedThreadPool(10);

    protected GeneralAutoResponder(Set<MainUnit> menuUnit,
                                   Sending sending,
                                   MessageService<T> messageService,
                                   UnitPointerRepository<MainUnit> unitPointerRepository
    ) {
        this.messageService = messageService;
        autoResponder = new AutoResponder<>(new UnitPointerServiceImpl<>(unitPointerRepository), menuUnit);
        init(sending);
    }

    private void init(Sending sending) {
        actionUnitMap.put(TypeUnit.CHECK, new AnswerCheckAction());
        actionUnitMap.put(TypeUnit.PROCESSING, new AnswerProcessingAction(sending));
        actionUnitMap.put(TypeUnit.TEXT, new AnswerTextAction(sending));
        actionUnitMap.put(TypeUnit.VALIDITY, new AnswerValidityAction());
    }

    public void initModifiables(List<Modifiable<T>> modifiables) {
        this.modifiables = modifiables;
    }

    protected void initActionUnit(String typeUnit, ActionUnit<? super MainUnit, T> actionUnit) {
        if (!actionUnitMap.containsKey(typeUnit)) {
            actionUnitMap.put(typeUnit, actionUnit);
        } else {
            throw new ConfigAppException("Обработка такого типа юнита уже зарегистрирована");
        }
    }

    public <U extends MainUnit> void initDefaultUnit(U defaultUnit) {
        autoResponder.setDefaultUnit(defaultUnit);
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

    public void checkNewMessage() {
        List<T> eventByTime = messageService.getNewMessage();
        if (eventByTime != null && !eventByTime.isEmpty()) {
            executorService.execute(
                    () -> eventByTime.parallelStream().forEach(processing())
            );
        }
    }

    private Consumer<T> processing() {
        return event -> {
            if (modifiables != null) {
                modifiables.forEach(modifiable -> modifiable.change(event));
            }
            autoResponder.answer(event.getPersonId(), event.getText()).ifPresent(unitAnswer -> answer(event, unitAnswer));
        };
    }

    public void answer(T event, MainUnit unitAnswer) {
        unitAnswer = getAction(event, unitAnswer);
        unitAnswer = activeUnitAfter(unitAnswer, event);
        if (!(autoResponder.getDefaultUnit() != null && autoResponder.getDefaultUnit().equals(unitAnswer))) {
            autoResponder.getUnitPointerService().save(new UnitPointer<>(event.getPersonId(), unitAnswer));
        }
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

    private MainUnit getAction(T event, MainUnit unitAnswer) {
        if (actionUnitMap.containsKey(unitAnswer.getType())) {
            ActionUnit actionUnit = actionUnitMap.get(unitAnswer.getType());
            MainUnit mainUnit = actionUnit.action(unitAnswer, event);
            if (!unitAnswer.equals(mainUnit)) return getAction(event, mainUnit);
            return mainUnit;
        }
        throw new NotFoundException("ActionUnit для типа " + unitAnswer.getType() + " не зарегистрирован");
    }

    @Override
    public void run() {
        checkNewMessage();
    }

}

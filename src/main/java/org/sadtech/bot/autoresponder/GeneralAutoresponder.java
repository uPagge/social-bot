package org.sadtech.bot.autoresponder;

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
import org.sadtech.bot.core.sender.Sent;
import org.sadtech.bot.core.service.EventService;

import java.util.*;

public abstract class GeneralAutoresponder<T extends Content> implements Runnable {

    private EventService<T> eventService;
    protected Autoresponder autoresponder;
    protected Sent sent;
    protected Map<TypeUnit, ActionUnit> actionUnitMap;

    protected GeneralAutoresponder(Sent sent, EventService<T> eventService) {
        this.eventService = eventService;
        this.sent = sent;
        autoresponder = new Autoresponder(new UnitPointerServiceImpl());
        init(sent);
    }

    private void init(Sent sent) {
        actionUnitMap = new EnumMap<>(TypeUnit.class);
        actionUnitMap.put(TypeUnit.CHECK, new AnswerCheckAction(actionUnitMap, autoresponder.getUnitPointerService()));
        actionUnitMap.put(TypeUnit.PROCESSING, new AnswerProcessingAction(sent));
        actionUnitMap.put(TypeUnit.SAVE, new AnswerSaveAction());
        actionUnitMap.put(TypeUnit.TEXT, new AnswerTextAction(sent));
        actionUnitMap.put(TypeUnit.TIMER, new AnswerTimerAction(new TimerActionServiceImpl(new TimerActionRepositoryList()), actionUnitMap));
        actionUnitMap.put(TypeUnit.YES_OR_NO, new AnswerValidityAction(actionUnitMap, autoresponder.getUnitPointerService()));
    }

    public void setMenuUnit(Set<Unit> menuUnit) {
        this.autoresponder.setMenuUnits(menuUnit);
    }

    private void checkNewMessages() {
        Long oldData = new Date().getTime() / 1000 - 1;
        Long newData;
        while (true) {
            newData = new Date().getTime() / 1000 - 1;
            if (oldData < newData) {
                List<T> mailList = eventService.getFirstEventByTime(Integer.parseInt(oldData.toString()), Integer.parseInt(newData.toString()));
                if (mailList.size() > 0) {
                    this.sendReply(mailList);
                }
            }
            oldData = new Long(newData.toString());
        }
    }

    public abstract void sendReply(List<T> mailList);

    protected void activeUnitAfter(MainUnit mainUnit, T content) {
        if (mainUnit.getNextUnits() != null) {
            mainUnit.getNextUnits().stream()
                    .filter(unit -> unit instanceof MainUnit)
                    .map(unit -> (MainUnit) unit)
                    .forEach(nextUnit -> {
                        if (nextUnit.getUnitActiveStatus().equals(UnitActiveStatus.AFTER)) {
                            actionUnitMap.get(nextUnit.getTypeUnit()).action(nextUnit, content);
                            autoresponder.getUnitPointerService().getByEntityId(content.getPersonId()).setUnit(nextUnit);
                        }
                    });
        }
    }

    protected void addActionUnit(TypeUnit typeUnit, ActionUnit actionUnit) {
        actionUnitMap.put(typeUnit, actionUnit);
    }

    @Override
    public void run() {
        checkNewMessages();
    }

}

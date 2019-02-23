package org.sadtech.vkbot.autoresponder;

import org.sadtech.autoresponder.Autoresponder;
import org.sadtech.autoresponder.entity.Unit;
import org.sadtech.autoresponder.service.PersonService;
import org.sadtech.autoresponder.service.PersonServiceImpl;
import org.sadtech.vkbot.autoresponder.action.*;
import org.sadtech.vkbot.autoresponder.entity.unit.MainUnit;
import org.sadtech.vkbot.autoresponder.entity.unit.TypeUnit;
import org.sadtech.vkbot.autoresponder.entity.unit.UnitActiveStatus;
import org.sadtech.vkbot.autoresponder.timer.impl.TimerActionRepositoryList;
import org.sadtech.vkbot.autoresponder.timer.impl.TimerActionServiceImpl;
import org.sadtech.vkbot.core.entity.Mail;
import org.sadtech.vkbot.core.sender.Sent;
import org.sadtech.vkbot.core.service.distribution.impl.EventService;

import java.util.*;

public abstract class AutoresponderMain<T> implements Runnable {

    private EventService<T> eventService;
    protected Autoresponder autoresponder;
    protected Sent sent;
    protected Map<TypeUnit, ActionUnit> actionUnitMap;

    public AutoresponderMain(Sent sent, EventService<T> eventService) {
        this.eventService = eventService;
        this.sent = sent;
        PersonService personService = new PersonServiceImpl();
        autoresponder = new Autoresponder(personService);
        init(sent);
    }

    private void init(Sent sent) {
        actionUnitMap = new HashMap<>();
        actionUnitMap.put(TypeUnit.CHECK, new AnswerCheckAction(actionUnitMap));
        actionUnitMap.put(TypeUnit.PROCESSING, new AnswerProcessingAction(sent));
        actionUnitMap.put(TypeUnit.SAVE, new AnswerSaveAction());
        actionUnitMap.put(TypeUnit.TEXT, new AnswerTextAction(sent));
        actionUnitMap.put(TypeUnit.TIMER, new AnswerTimerAction(new TimerActionServiceImpl(new TimerActionRepositoryList()), actionUnitMap));
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

    abstract void sendReply(List<T> mailList);

    protected void activeUnitAfter(MainUnit mainUnit, Mail mail) {
        if (mainUnit.getNextUnits() != null) {
            mainUnit.getNextUnits().stream().filter(Unit -> Unit instanceof MainUnit).map(unit -> (MainUnit) unit).forEach(nextUnit -> {
                if (nextUnit.getUnitActiveStatus().equals(UnitActiveStatus.AFTER)) {
                    actionUnitMap.get(nextUnit.getTypeUnit()).action(nextUnit, mail.getBody(), mail.getPerson().getId());
                    autoresponder.getPersonService().getPersonById(mail.getPerson().getId()).setUnit(nextUnit);
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

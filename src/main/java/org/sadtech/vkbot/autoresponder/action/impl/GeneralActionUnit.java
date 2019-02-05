package org.sadtech.vkbot.autoresponder.action.impl;

import org.sadtech.autoresponder.entity.Unit;
import org.sadtech.autoresponder.service.PersonService;
import org.sadtech.vkbot.autoresponder.action.Action;
import org.sadtech.vkbot.autoresponder.action.ActionUnit;
import org.sadtech.vkbot.autoresponder.entity.TimerAnswer;
import org.sadtech.vkbot.autoresponder.service.ActionService;
import org.sadtech.vkbot.core.entity.Mail;
import org.sadtech.vkbot.core.sender.MailSandler;

public class GeneralActionUnit implements Action {

    private MailSandler mailSandler;
    private PersonService personServiceAutoresponder;
    private ActionService actionService;

    public void setPersonServiceAutoresponder(PersonService personServiceAutoresponder) {
        this.personServiceAutoresponder = personServiceAutoresponder;
    }

    public GeneralActionUnit(MailSandler mailSandler, PersonService personServiceAutoresponder, ActionService actionService) {
        this.mailSandler = mailSandler;
        this.personServiceAutoresponder = personServiceAutoresponder;
        this.actionService = actionService;
    }

    public GeneralActionUnit(MailSandler mailSandler, ActionService actionService) {
        this.mailSandler = mailSandler;
        this.actionService = actionService;
    }

    public void action(Unit unit, Mail mail) {
        ActionUnit actionUnit = actionService.get(unit.getClass());
        actionUnit.action(unit, mail);

        if (unit.getNextUnits()!=null) {
            for (Unit nextUnit : unit.getNextUnits()) {
                if (nextUnit.getClass().equals(TimerAnswer.class) && !nextUnit.equals(unit)) {
                    action(nextUnit, mail);
                    personServiceAutoresponder.getPersonById(mail.getPerson().getId()).setUnit(nextUnit);
                }
            }
        }
    }

    public ActionService getActionService() {
        return actionService;
    }

    public void registerActionUnit(Class clazz, ActionUnit actionUnit) {
        actionService.put(clazz, actionUnit);
    }

    public MailSandler getMailSandler() {
        return mailSandler;
    }

    public PersonService getPersonServiceAutoresponder() {
        return personServiceAutoresponder;
    }


}

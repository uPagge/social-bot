package org.sadtech.vkbot.autoresponder.action.impl;

import org.sadtech.autoresponder.service.PersonService;
import org.sadtech.vkbot.autoresponder.action.Action;
import org.sadtech.vkbot.autoresponder.action.ActionUnit;
import org.sadtech.vkbot.autoresponder.entity.MainUnit;
import org.sadtech.vkbot.autoresponder.service.ActionService;
import org.sadtech.vkbot.core.entity.Mail;

public class GeneralActionUnit implements Action {

    private ActionService actionService;
    private PersonService personService;

    public GeneralActionUnit(PersonService personServiceAutoresponder, ActionService actionService) {
        this.actionService = actionService;
        this.personService = personServiceAutoresponder;
    }

    public GeneralActionUnit(ActionService actionService) {
        this.actionService = actionService;
    }

    public void action(MainUnit unit, Mail mail) {
        ActionUnit actionUnit = actionService.get(unit.getClass());
        actionUnit.action(unit, mail);
        personService.getPersonById(mail.getPerson().getId()).setUnit(unit);
    }

    public void registerActionUnit(Class clazz, ActionUnit actionUnit) {
        actionService.put(clazz, actionUnit);
    }

    public void setPersonService(PersonService personService) {
        this.personService = personService;
    }
}

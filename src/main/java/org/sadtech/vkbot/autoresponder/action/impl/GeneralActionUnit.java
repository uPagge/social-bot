package org.sadtech.vkbot.autoresponder.action.impl;

import org.sadtech.autoresponder.service.PersonService;
import org.sadtech.vkbot.autoresponder.action.Action;
import org.sadtech.vkbot.autoresponder.action.ActionUnit;
import org.sadtech.vkbot.autoresponder.entity.unit.MainUnit;
import org.sadtech.vkbot.autoresponder.service.ActionService;

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


    @Override
    public void action(MainUnit unit, String message, Integer idPerson) {
        ActionUnit actionUnit = actionService.get(unit.getClass());
        actionUnit.action(unit, message, idPerson);
        personService.getPersonById(idPerson).setUnit(unit);
    }

    @Override
    public void registerActionUnit(Class clazz, ActionUnit actionUnit) {
        actionService.put(clazz, actionUnit);
    }

    @Override
    public void setPersonService(PersonService personService) {
        this.personService = personService;
    }
}

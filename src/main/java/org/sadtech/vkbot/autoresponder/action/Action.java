package org.sadtech.vkbot.autoresponder.action;

import org.sadtech.autoresponder.service.PersonService;
import org.sadtech.vkbot.autoresponder.entity.unit.MainUnit;

public interface Action {

    void action(MainUnit unit, String message, Integer idPerson);

    void registerActionUnit(Class clazz, ActionUnit actionUnit);

    void setPersonService(PersonService personService);

}

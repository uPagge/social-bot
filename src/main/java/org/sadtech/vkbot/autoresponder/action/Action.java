package org.sadtech.vkbot.autoresponder.action;

import org.sadtech.autoresponder.service.PersonService;
import org.sadtech.vkbot.autoresponder.entity.MainUnit;
import org.sadtech.vkbot.core.entity.Mail;

public interface Action {

    void action(MainUnit unit, Mail mail);

    void registerActionUnit(Class clazz, ActionUnit actionUnit);

    void setPersonService(PersonService personService);

}

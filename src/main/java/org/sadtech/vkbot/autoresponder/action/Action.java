package org.sadtech.vkbot.autoresponder.action;

import org.sadtech.autoresponder.entity.Unit;
import org.sadtech.autoresponder.service.PersonService;
import org.sadtech.vkbot.autoresponder.service.ActionService;
import org.sadtech.vkbot.core.entity.Mail;
import org.sadtech.vkbot.core.sender.MailSandler;

public interface Action {

    void action(Unit unit, Mail mail);

    void registerActionUnit(Class clazz, ActionUnit actionUnit);

    MailSandler getMailSandler();

    PersonService getPersonServiceAutoresponder ();

    ActionService getActionService();
}

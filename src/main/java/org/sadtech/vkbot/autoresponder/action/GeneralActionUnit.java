package org.sadtech.vkbot.autoresponder.action;

import org.sadtech.autoresponder.entity.Unit;
import org.sadtech.vkbot.core.entity.Mail;
import org.sadtech.vkbot.core.sender.MailSandler;

import java.util.HashMap;
import java.util.Map;

public class GeneralActionUnit implements Action {

    private MailSandler mailSandler;
    private Map<Class, ActionUnit> actionUnitHashMap;

    public GeneralActionUnit(MailSandler mailSandler) {
        this.mailSandler = mailSandler;
        actionUnitHashMap = new HashMap<>();
    }

    public void action(Unit unit, Mail mail) {
        ActionUnit actionUnit = actionUnitHashMap.get(unit.getClass());
        actionUnit.action(unit, mail);
    }

    public void registerActionUnit(Class clazz, ActionUnit actionUnit) {
        actionUnitHashMap.put(clazz, actionUnit);
    }

    public MailSandler getMailSandler() {
        return mailSandler;
    }
}

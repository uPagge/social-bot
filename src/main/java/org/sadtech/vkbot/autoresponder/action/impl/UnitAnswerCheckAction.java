package org.sadtech.vkbot.autoresponder.action.impl;

import org.apache.log4j.Logger;
import org.sadtech.autoresponder.entity.Unit;
import org.sadtech.vkbot.autoresponder.action.Action;
import org.sadtech.vkbot.autoresponder.action.ActionUnit;
import org.sadtech.vkbot.autoresponder.entity.UnitAnswerCheck;
import org.sadtech.vkbot.core.entity.Mail;

public class UnitAnswerCheckAction implements ActionUnit {

    public static final Logger log = Logger.getLogger(UnitAnswerCheckAction.class);

    private Action action;

    public UnitAnswerCheckAction(Action generalActionUnit) {
        generalActionUnit.registerActionUnit(UnitAnswerCheck.class, this);
        action = generalActionUnit;
    }

    @Override
    public void action(Unit unit, Mail mail) {
        UnitAnswerCheck unitAnswerCheck = (UnitAnswerCheck) unit;
        unitAnswerCheck.setUserId(mail.getPerson().getId());
        if (unitAnswerCheck.getCheck().checked()) {
            log.info("Проверка пройдена");
            action.action(unitAnswerCheck.getUnitTrue(), mail);
        } else {
            log.info("Проверка провалена");
            action.action(unitAnswerCheck.getUnitFalse(), mail);
        }

    }
}

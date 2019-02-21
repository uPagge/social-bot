package org.sadtech.vkbot.autoresponder.action.impl;

import org.apache.log4j.Logger;
import org.sadtech.autoresponder.entity.Unit;
import org.sadtech.vkbot.autoresponder.action.Action;
import org.sadtech.vkbot.autoresponder.action.ActionUnit;
import org.sadtech.vkbot.autoresponder.entity.unit.UnitAnswerCheck;

public class UnitAnswerCheckAction implements ActionUnit {

    public static final Logger log = Logger.getLogger(UnitAnswerCheckAction.class);

    private Action action;

    public UnitAnswerCheckAction(Action generalActionUnit) {
        generalActionUnit.registerActionUnit(UnitAnswerCheck.class, this);
        action = generalActionUnit;
    }

    @Override
    public void action(Unit unit, String message, Integer idPerson) {
        UnitAnswerCheck unitAnswerCheck = (UnitAnswerCheck) unit;
        unitAnswerCheck.setUserId(idPerson);
        if (unitAnswerCheck.getCheck().checked()) {
            log.info("Проверка пройдена");
            action.action(unitAnswerCheck.getUnitTrue(), message, idPerson);
        } else {
            log.info("Проверка провалена");
            action.action(unitAnswerCheck.getUnitFalse(), message, idPerson);
        }
    }
}

package org.sadtech.vkbot.autoresponder.action.impl;

import org.apache.log4j.Logger;
import org.sadtech.vkbot.autoresponder.action.ActionUnit;
import org.sadtech.vkbot.autoresponder.entity.unit.MainUnit;
import org.sadtech.vkbot.autoresponder.entity.unit.TypeUnit;
import org.sadtech.vkbot.autoresponder.entity.unit.UnitAnswerCheck;

import java.util.Map;

public class UnitAnswerCheckAction implements ActionUnit<UnitAnswerCheck> {

    public static final Logger log = Logger.getLogger(UnitAnswerCheckAction.class);

    private Map<TypeUnit, ActionUnit> actionUnitMap;

    public UnitAnswerCheckAction(Map<TypeUnit, ActionUnit> actionUnitMap) {
        this.actionUnitMap = actionUnitMap;
    }

    @Override
    public void action(UnitAnswerCheck unitAnswerCheck, String message, Integer idPerson) {
        unitAnswerCheck.setUserId(idPerson);
        MainUnit unitAnswer;
        if (unitAnswerCheck.getCheck().checked()) {
            log.info("Проверка пройдена");
            unitAnswer = unitAnswerCheck.getUnitTrue();
        } else {
            log.info("Проверка не пройдена");
            unitAnswer = unitAnswerCheck.getUnitFalse();
        }
        if (unitAnswer!=null) {
            actionUnitMap.get(unitAnswer.getTypeUnit()).action(unitAnswer, message, idPerson);
        }
    }
}

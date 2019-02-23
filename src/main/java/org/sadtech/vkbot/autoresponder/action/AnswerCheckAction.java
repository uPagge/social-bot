package org.sadtech.vkbot.autoresponder.action;

import org.apache.log4j.Logger;
import org.sadtech.vkbot.autoresponder.domain.unit.MainUnit;
import org.sadtech.vkbot.autoresponder.domain.unit.TypeUnit;
import org.sadtech.vkbot.autoresponder.domain.unit.AnswerCheck;

import java.util.Map;

public class AnswerCheckAction implements ActionUnit<AnswerCheck> {

    public static final Logger log = Logger.getLogger(AnswerCheckAction.class);

    private Map<TypeUnit, ActionUnit> actionUnitMap;

    public AnswerCheckAction(Map<TypeUnit, ActionUnit> actionUnitMap) {
        this.actionUnitMap = actionUnitMap;
    }

    @Override
    public void action(AnswerCheck answerCheck, String message, Integer idPerson) {
        answerCheck.setUserId(idPerson);
        MainUnit unitAnswer;
        if (answerCheck.getCheck().checked()) {
            log.info("Проверка пройдена");
            unitAnswer = answerCheck.getUnitTrue();
        } else {
            log.info("Проверка не пройдена");
            unitAnswer = answerCheck.getUnitFalse();
        }
        if (unitAnswer!=null) {
            actionUnitMap.get(unitAnswer.getTypeUnit()).action(unitAnswer, message, idPerson);
        }
    }
}

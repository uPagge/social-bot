package org.sadtech.bot.autoresponder.action;

import org.apache.log4j.Logger;
import org.sadtech.autoresponder.service.UnitPointerService;
import org.sadtech.bot.autoresponder.domain.unit.AnswerCheck;
import org.sadtech.bot.autoresponder.domain.unit.MainUnit;
import org.sadtech.bot.autoresponder.domain.unit.TypeUnit;

import java.util.Map;

public class AnswerCheckAction implements ActionUnit<AnswerCheck> {

    private static final Logger log = Logger.getLogger(AnswerCheckAction.class);

    private final Map<TypeUnit, ActionUnit> actionUnitMap;
    private final UnitPointerService unitPointerService;

    public AnswerCheckAction(Map<TypeUnit, ActionUnit> actionUnitMap, UnitPointerService unitPointerService) {
        this.actionUnitMap = actionUnitMap;
        this.unitPointerService = unitPointerService;
    }

    @Override
    public void action(AnswerCheck answerCheck, String message, Integer idPerson) {
        MainUnit unitAnswer;
        if (answerCheck.getCheck().checked(idPerson, message)) {
            log.info("Проверка пройдена");
            unitAnswer = answerCheck.getUnitTrue();
        } else {
            log.info("Проверка не пройдена");
            unitAnswer = answerCheck.getUnitFalse();
        }
        if (unitAnswer!=null) {
            actionUnitMap.get(unitAnswer.getTypeUnit()).action(unitAnswer, message, idPerson);
            unitPointerService.getByEntityId(idPerson).setUnit(unitAnswer);
        }
    }
}

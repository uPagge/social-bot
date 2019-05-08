package org.sadtech.bot.autoresponder.service.action;

import org.apache.log4j.Logger;
import org.sadtech.autoresponder.service.UnitPointerService;
import org.sadtech.bot.autoresponder.domain.unit.AnswerCheck;
import org.sadtech.bot.autoresponder.domain.unit.MainUnit;
import org.sadtech.bot.autoresponder.domain.unit.TypeUnit;
import org.sadtech.bot.core.domain.Mail;

import java.util.Map;

public class AnswerCheckAction implements ActionUnit<AnswerCheck, Mail> {

    private static final Logger log = Logger.getLogger(AnswerCheckAction.class);

    private final Map<TypeUnit, ActionUnit> actionUnitMap;
    private final UnitPointerService unitPointerService;

    public AnswerCheckAction(Map<TypeUnit, ActionUnit> actionUnitMap, UnitPointerService unitPointerService) {
        this.actionUnitMap = actionUnitMap;
        this.unitPointerService = unitPointerService;
    }

    @Override
    public MainUnit action(AnswerCheck answerCheck, Mail mail) {
        MainUnit unitAnswer;
        if (answerCheck.getCheck().checked(mail.getPersonId(), mail.getMessage())) {
            log.info("Проверка пройдена");
            unitAnswer = answerCheck.getUnitTrue();
        } else {
            log.info("Проверка не пройдена");
            unitAnswer = answerCheck.getUnitFalse();
        }
        if (unitAnswer != null) {
            unitPointerService.getByEntityId(mail.getPersonId()).setUnit(unitAnswer);
            actionUnitMap.get(unitAnswer.getTypeUnit()).action(unitAnswer, mail);
        }
        return unitAnswer;
    }
}

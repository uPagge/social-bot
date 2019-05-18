package org.sadtech.bot.autoresponder.service.action;

import org.sadtech.autoresponder.Autoresponder;
import org.sadtech.autoresponder.service.UnitPointerService;
import org.sadtech.bot.autoresponder.domain.unit.AnswerNext;
import org.sadtech.bot.autoresponder.domain.unit.MainUnit;
import org.sadtech.bot.autoresponder.domain.unit.TypeUnit;
import org.sadtech.bot.core.domain.Mail;

import java.util.Map;

public class AnswerNextAction implements ActionUnit<AnswerNext, Mail> {

    private final Autoresponder autoresponder;
    private final Map<TypeUnit, ActionUnit> actionUnitMap;
    private final UnitPointerService unitPointerService;

    public AnswerNextAction(Autoresponder autoresponder, Map<TypeUnit, ActionUnit> actionUnitMap, UnitPointerService unitPointerService) {
        this.autoresponder = autoresponder;
        this.actionUnitMap = actionUnitMap;
        this.unitPointerService = unitPointerService;
    }

    @Override
    public MainUnit action(AnswerNext unit, Mail mail) {
        MainUnit answer = (MainUnit) autoresponder.answer(mail.getPersonId(), mail.getMessage());
        actionUnitMap.get(answer.getTypeUnit()).action(answer, mail);
        unitPointerService.edit(mail.getPersonId(), answer);
        return answer;
    }

}

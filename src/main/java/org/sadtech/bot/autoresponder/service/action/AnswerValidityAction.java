package org.sadtech.bot.autoresponder.service.action;

import javafx.util.Pair;
import org.sadtech.autoresponder.service.UnitPointerService;
import org.sadtech.bot.autoresponder.domain.unit.AnswerText;
import org.sadtech.bot.autoresponder.domain.unit.AnswerValidity;
import org.sadtech.bot.autoresponder.domain.unit.MainUnit;
import org.sadtech.bot.autoresponder.domain.unit.TypeUnit;
import org.sadtech.bot.core.domain.Mail;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AnswerValidityAction implements ActionUnit<AnswerValidity, Mail> {

    private final Map<TypeUnit, ActionUnit> actionUnitMap;
    private final UnitPointerService unitPointerService;
    public static final Set<String> WORDS_YES = Stream.of("да", "WORDS_YES", "ага").collect(Collectors.toSet());
    public static final Set<String> WORDS_NO = Stream.of("нет", "WORDS_NO", "неа").collect(Collectors.toSet());

    public AnswerValidityAction(Map<TypeUnit, ActionUnit> actionUnitMap, UnitPointerService unitPointerService) {
        this.actionUnitMap = actionUnitMap;
        this.unitPointerService = unitPointerService;
    }

    @Override
    public MainUnit action(AnswerValidity unit, Mail mail) {
        String message = mail.getMessage();
        Integer personId = mail.getPersonId();
        if (WORDS_YES.contains(message.toLowerCase())) {
            String save = unit.getTempSave().load(personId);
            return actionUnit(unit.getYes(), getNewMail(mail, save));
        } else if (WORDS_NO.contains(message.toLowerCase())) {
            String save = unit.getTempSave().load(personId);
            return actionUnit(unit.getNo(), getNewMail(mail, save));
        } else {
            Pair<String, String> save = unit.getTestInsert().insert(personId, message);
            if (save.getValue() == null) {
                return actionUnit(unit.getDataNull(), mail);
            } else {
                unit.getTempSave().save(personId, save.getValue());
                AnswerText answerText = AnswerText.builder().message(save.getKey()).nextUnit(unit).build();
                return actionUnit(answerText, mail);
            }
        }
    }

    private Mail getNewMail(Mail mail, String save) {
        Mail newMail = mail.prototype();
        newMail.setMessage(save);
        return newMail;
    }

    private MainUnit actionUnit(MainUnit mainUnit, Mail mail) {
        actionUnitMap.get(mainUnit.getTypeUnit()).action(mainUnit, mail);
        unitPointerService.getByEntityId(mail.getPersonId()).setUnit(mainUnit);
        return mainUnit;
    }
}

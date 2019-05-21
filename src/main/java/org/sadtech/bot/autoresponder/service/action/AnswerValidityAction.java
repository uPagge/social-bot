package org.sadtech.bot.autoresponder.service.action;

import javafx.util.Pair;
import org.sadtech.bot.autoresponder.domain.unit.AnswerText;
import org.sadtech.bot.autoresponder.domain.unit.AnswerValidity;
import org.sadtech.bot.autoresponder.domain.unit.MainUnit;
import org.sadtech.bot.core.domain.BoxAnswer;
import org.sadtech.bot.core.domain.Mail;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AnswerValidityAction implements ActionUnit<AnswerValidity, Mail> {

    public static final Set<String> WORDS_YES = Stream.of("да", "ага").collect(Collectors.toSet());
    public static final Set<String> WORDS_NO = Stream.of("нет", "неа").collect(Collectors.toSet());

    @Override
    public MainUnit action(AnswerValidity unit, Mail mail) {
        String message = mail.getMessage();
        Integer personId = mail.getPersonId();
        if (WORDS_YES.contains(message.toLowerCase())) {
            String save = unit.getTempSave().load(personId);
            getNewMail(mail, save);
            return unit.getUnitYes();
        } else if (WORDS_NO.contains(message.toLowerCase())) {
            String save = unit.getTempSave().load(personId);
            getNewMail(mail, save);
            return unit.getUnitNo();
        } else {
            Pair<String, String> save = unit.getTestInsert().insert(personId, message);
            if (save.getValue() == null) {
                return unit.getUnitNull();
            } else {
                unit.getTempSave().save(personId, save.getValue());
                AnswerText answerText = new AnswerText(BoxAnswer.builder().message(save.getKey()).build());
                answerText.setNextUnit(unit);
                return answerText;
            }
        }
    }

    private Mail getNewMail(Mail mail, String save) {
        Mail newMail = mail.prototype();
        newMail.setMessage(save);
        return newMail;
    }

}

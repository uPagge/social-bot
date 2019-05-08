package org.sadtech.bot.autoresponder.service.action;

import org.sadtech.bot.autoresponder.domain.unit.AnswerSave;
import org.sadtech.bot.autoresponder.domain.unit.MainUnit;
import org.sadtech.bot.autoresponder.saver.SaveStatus;
import org.sadtech.bot.core.domain.Mail;

import java.util.Set;

public class AnswerSaveAction implements ActionUnit<AnswerSave, Mail> {

    @Override
    public MainUnit action(AnswerSave answerSave, Mail mail) {
        Set<SaveStatus> unitSaveStatus = answerSave.getSaveStatuses();
        if (unitSaveStatus.contains(SaveStatus.INIT)) {
            answerSave.getSavable().init(mail.getPersonId());
        }

        if (unitSaveStatus.contains(SaveStatus.SAVE)) {
            answerSave.getSavable().save(mail.getPersonId(), answerSave.getKey(), mail.getMessage());
        }

        if (unitSaveStatus.contains(SaveStatus.FINISH)) {
            answerSave.getSavable().push(mail.getPersonId());
        }
        return answerSave;
    }
}

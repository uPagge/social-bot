package org.sadtech.bot.autoresponder.service.action;

import javafx.util.Pair;
import org.sadtech.bot.autoresponder.domain.unit.AnswerSave;
import org.sadtech.bot.autoresponder.domain.unit.MainUnit;
import org.sadtech.bot.autoresponder.domain.usercode.SaveData;
import org.sadtech.bot.autoresponder.save.Savable;
import org.sadtech.bot.autoresponder.save.SaveStatus;
import org.sadtech.bot.core.domain.content.Mail;

import java.util.Set;

public class AnswerSaveAction implements ActionUnit<AnswerSave, Mail> {

    @Override
    public MainUnit action(AnswerSave answerSave, Mail mail) {
        Set<SaveStatus> unitSaveStatus = answerSave.getSaveStatuses();
        Savable savable = answerSave.getSavable();
        if (unitSaveStatus.contains(SaveStatus.INIT)) {
            savable.init(mail.getPersonId());
        }

        if (unitSaveStatus.contains(SaveStatus.SAVE)) {
            SaveData saveData = answerSave.getSaveData();
            if (saveData != null) {
                savable.save(mail.getPersonId(), saveData.save(mail.getPersonId(), mail.getMessage()));
            } else {
                savable.save(mail.getPersonId(), new Pair<>(answerSave.getKey(), mail.getMessage()));
            }
        }

        if (unitSaveStatus.contains(SaveStatus.FINISH)) {
            savable.push(mail.getPersonId());
        }
        return answerSave;
    }
}

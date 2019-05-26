package org.sadtech.bot.autoresponder.service.action;

import javafx.util.Pair;
import org.sadtech.bot.autoresponder.domain.unit.AnswerSave;
import org.sadtech.bot.autoresponder.domain.unit.MainUnit;
import org.sadtech.bot.autoresponder.service.save.Savable;
import org.sadtech.bot.autoresponder.service.save.SaveStatus;
import org.sadtech.bot.autoresponder.service.usercode.SaveData;
import org.sadtech.bot.core.domain.content.Mail;

import java.util.Set;

public class AnswerSaveAction implements ActionUnit<AnswerSave, Mail> {

    @Override
    public MainUnit action(AnswerSave answerSave, Mail mail) {
        Set<SaveStatus> unitSaveStatus = answerSave.getSaveStatuses();
        Savable savable = answerSave.getSavable();
        if (unitSaveStatus.contains(SaveStatus.INIT) || unitSaveStatus.contains(SaveStatus.FULL)) {
            savable.init(mail.getPersonId());
        }

        if (unitSaveStatus.contains(SaveStatus.SAVE) || unitSaveStatus.contains(SaveStatus.FULL)) {
            SaveData saveData = answerSave.getSaveData();
            if (saveData != null) {
                savable.save(mail.getPersonId(), saveData.save(mail));
            } else {
                savable.save(mail.getPersonId(), new Pair<>(answerSave.getKey(), mail.getMessage()));
            }
        }

        if (unitSaveStatus.contains(SaveStatus.FINISH) || unitSaveStatus.contains(SaveStatus.FULL)) {
            savable.push(mail.getPersonId());
        }
        return answerSave;
    }
}

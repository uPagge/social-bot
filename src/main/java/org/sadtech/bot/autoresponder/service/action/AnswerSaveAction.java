package org.sadtech.bot.autoresponder.service.action;

import org.sadtech.bot.autoresponder.domain.unit.AnswerSave;
import org.sadtech.bot.autoresponder.domain.unit.MainUnit;
import org.sadtech.bot.autoresponder.saver.SaveStatus;

import java.util.Set;

public class AnswerSaveAction implements ActionUnit<AnswerSave> {

    @Override
    public MainUnit action(AnswerSave answerSave, String message, Integer userId) {
        Set<SaveStatus> unitSaveStatus = answerSave.getSaveStatuses();
        if (unitSaveStatus.contains(SaveStatus.INIT)) {
            answerSave.getSavable().init(userId);
        }

        if (unitSaveStatus.contains(SaveStatus.SAVE)) {
            answerSave.getSavable().save(userId, answerSave.getKey(), message);
        }

        if (unitSaveStatus.contains(SaveStatus.FINISH)) {
            answerSave.getSavable().push(userId);
        }
        return answerSave;
    }
}

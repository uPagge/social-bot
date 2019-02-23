package org.sadtech.vkbot.autoresponder.action;

import org.sadtech.vkbot.autoresponder.domain.unit.AnswerSave;
import org.sadtech.vkbot.autoresponder.saver.SaveStatus;

import java.util.Set;

public class AnswerSaveAction implements ActionUnit<AnswerSave> {

    @Override
    public void action(AnswerSave answerSave, String message, Integer idPerson) {
        Set<SaveStatus> unitSaveStatus = answerSave.getSaveStatuses();
        if (unitSaveStatus.contains(SaveStatus.INIT)) {
            answerSave.getSavable().init(idPerson);
        }

        if (unitSaveStatus.contains(SaveStatus.SAVE)) {
            answerSave.getSavable().save(idPerson, answerSave.getKey(), message);
        }

        if (unitSaveStatus.contains(SaveStatus.FINISH)) {
            answerSave.getSavable().push(idPerson);
        }
    }
}

package org.sadtech.vkbot.autoresponder.action.impl;

import org.sadtech.vkbot.autoresponder.action.ActionUnit;
import org.sadtech.vkbot.autoresponder.entity.unit.AnswerSave;
import org.sadtech.vkbot.autoresponder.entity.unit.MainUnit;
import org.sadtech.vkbot.autoresponder.saver.SaveStatus;

import java.util.Set;

public class AnswerSaveAction implements ActionUnit {

    @Override
    public void action(MainUnit unit, String message, Integer idPerson) {
        AnswerSave answerSave = (AnswerSave) unit;

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

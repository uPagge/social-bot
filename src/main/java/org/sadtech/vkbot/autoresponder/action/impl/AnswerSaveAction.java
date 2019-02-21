package org.sadtech.vkbot.autoresponder.action.impl;

import org.sadtech.autoresponder.entity.Unit;
import org.sadtech.vkbot.autoresponder.action.Action;
import org.sadtech.vkbot.autoresponder.action.ActionUnit;
import org.sadtech.vkbot.autoresponder.entity.unit.AnswerSave;
import org.sadtech.vkbot.autoresponder.saver.SaveStatus;
import org.sadtech.vkbot.core.sender.Sent;

import java.util.Set;

public class AnswerSaveAction implements ActionUnit {

    private Sent sent;

    public AnswerSaveAction(Action generalActionUnit, Sent sent) {
        generalActionUnit.registerActionUnit(AnswerSave.class, this);
        this.sent = sent;
    }

    public AnswerSaveAction(Sent sent) {
        this.sent = sent;
    }


    @Override
    public void action(Unit unit, String message, Integer idPerson) {
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

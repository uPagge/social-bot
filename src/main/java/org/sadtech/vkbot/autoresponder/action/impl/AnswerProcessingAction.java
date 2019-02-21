package org.sadtech.vkbot.autoresponder.action.impl;

import org.sadtech.autoresponder.entity.Unit;
import org.sadtech.vkbot.autoresponder.action.Action;
import org.sadtech.vkbot.autoresponder.action.ActionUnit;
import org.sadtech.vkbot.autoresponder.entity.unit.AnswerProcessing;
import org.sadtech.vkbot.core.sender.Sent;

public class AnswerProcessingAction implements ActionUnit {

    private Sent sent;

    public AnswerProcessingAction(Action action, Sent sent) {
        action.registerActionUnit(AnswerProcessing.class, this);
        this.sent = sent;
    }

    @Override
    public void action(Unit unit, String message, Integer idPerson) {
        AnswerProcessing answerProcessing = (AnswerProcessing) unit;
        sent.send(idPerson, answerProcessing.getProcessingData().processing(message));
    }
}

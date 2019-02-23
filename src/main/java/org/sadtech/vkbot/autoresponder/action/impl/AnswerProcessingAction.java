package org.sadtech.vkbot.autoresponder.action.impl;

import org.sadtech.vkbot.autoresponder.action.ActionUnit;
import org.sadtech.vkbot.autoresponder.entity.unit.AnswerProcessing;
import org.sadtech.vkbot.core.sender.Sent;

public class AnswerProcessingAction implements ActionUnit<AnswerProcessing> {

    private Sent sent;

    public AnswerProcessingAction(Sent sent) {
        this.sent = sent;
    }

    @Override
    public void action(AnswerProcessing answerProcessing, String message, Integer idPerson) {
        sent.send(idPerson, answerProcessing.getProcessingData().processing(message));
    }
}

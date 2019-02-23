package org.sadtech.vkbot.autoresponder.action;

import org.sadtech.vkbot.autoresponder.domain.unit.AnswerProcessing;
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

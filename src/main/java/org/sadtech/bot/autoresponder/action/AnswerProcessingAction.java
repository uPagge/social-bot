package org.sadtech.bot.autoresponder.action;

import org.sadtech.bot.autoresponder.domain.unit.AnswerProcessing;
import org.sadtech.bot.core.sender.Sent;

public class AnswerProcessingAction implements ActionUnit<AnswerProcessing> {

    private final Sent sent;

    public AnswerProcessingAction(Sent sent) {
        this.sent = sent;
    }

    @Override
    public void action(AnswerProcessing answerProcessing, String message, Integer idPerson) {
        sent.send(idPerson, answerProcessing.getProcessingData().processing(message));
    }
}

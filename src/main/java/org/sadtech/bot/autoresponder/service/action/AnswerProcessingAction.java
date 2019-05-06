package org.sadtech.bot.autoresponder.service.action;

import org.sadtech.bot.autoresponder.domain.unit.AnswerProcessing;
import org.sadtech.bot.autoresponder.domain.unit.MainUnit;
import org.sadtech.bot.core.sender.Sent;

public class AnswerProcessingAction implements ActionUnit<AnswerProcessing> {

    private final Sent sent;

    public AnswerProcessingAction(Sent sent) {
        this.sent = sent;
    }

    @Override
    public MainUnit action(AnswerProcessing answerProcessing, String message, Integer userId) {
        sent.send(userId, answerProcessing.getProcessingData().processing(userId, message));
        return answerProcessing;
    }
}

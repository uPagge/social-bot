package org.sadtech.bot.autoresponder.service.action;

import org.sadtech.bot.autoresponder.domain.unit.AnswerProcessing;
import org.sadtech.bot.autoresponder.domain.unit.MainUnit;
import org.sadtech.bot.core.domain.BoxAnswer;
import org.sadtech.bot.core.domain.content.Message;
import org.sadtech.bot.core.service.sender.SendBox;
import org.sadtech.bot.core.service.sender.Sent;

public class AnswerProcessingAction implements ActionUnit<AnswerProcessing, Message> {

    private final Sent sent;

    public AnswerProcessingAction(Sent sent) {
        this.sent = sent;
    }

    @Override
    public MainUnit action(AnswerProcessing answerProcessing, Message message) {
        BoxAnswer boxAnswer = answerProcessing.getProcessingData().processing(message);
        if (answerProcessing.getSent() != null) {
            answerProcessing.getSent().send(message.getPersonId(), boxAnswer);
        } else {
            SendBox.sent(message, boxAnswer, sent);
        }
        return answerProcessing;
    }

}

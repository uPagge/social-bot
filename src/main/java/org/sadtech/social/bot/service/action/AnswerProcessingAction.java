package org.sadtech.social.bot.service.action;

import org.sadtech.social.bot.domain.unit.AnswerProcessing;
import org.sadtech.social.bot.domain.unit.MainUnit;
import org.sadtech.social.core.domain.BoxAnswer;
import org.sadtech.social.core.domain.content.Message;
import org.sadtech.social.core.service.sender.SendBox;
import org.sadtech.social.core.service.sender.Sent;

/**
 * Обработчик Unit-а {@link AnswerProcessing}.
 *
 * @author upagge [11/07/2019]
 */
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

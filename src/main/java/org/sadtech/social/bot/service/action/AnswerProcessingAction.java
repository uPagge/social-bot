package org.sadtech.social.bot.service.action;

import org.sadtech.social.bot.domain.unit.AnswerProcessing;
import org.sadtech.social.bot.domain.unit.MainUnit;
import org.sadtech.social.core.domain.BoxAnswer;
import org.sadtech.social.core.domain.content.Message;
import org.sadtech.social.core.service.sender.Sending;
import org.sadtech.social.core.utils.Sender;

/**
 * Обработчик Unit-а {@link AnswerProcessing}.
 *
 * @author upagge [11/07/2019]
 */
public class AnswerProcessingAction implements ActionUnit<AnswerProcessing<Message>, Message> {

    private final Sending sending;

    public AnswerProcessingAction(Sending sending) {
        this.sending = sending;
    }

    @Override
    public MainUnit action(AnswerProcessing<Message> answerProcessing, Message message) {
        BoxAnswer boxAnswer = answerProcessing.getProcessingData().processing(message);

        Sending answerProcessingSending = answerProcessing.getSending();
        if (answerProcessingSending != null) {
            Sender.sends(message, boxAnswer, answerProcessingSending);
        } else {
            Sender.sends(message, boxAnswer, this.sending);
        }
        return answerProcessing;
    }

}

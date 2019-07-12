package org.sadtech.social.bot.service.action;

import org.sadtech.social.bot.domain.unit.AnswerText;
import org.sadtech.social.bot.domain.unit.MainUnit;
import org.sadtech.social.core.domain.BoxAnswer;
import org.sadtech.social.core.domain.content.Message;
import org.sadtech.social.core.service.sender.SendBox;
import org.sadtech.social.core.service.sender.Sent;
import org.sadtech.social.core.utils.InsertWords;

import java.util.List;

/**
 * Обработчик Unit-а {@link AnswerText}.
 *
 * @author upagge [11/07/2019]
 */
public class AnswerTextAction implements ActionUnit<AnswerText, Message> {

    private final Sent sent;

    public AnswerTextAction(Sent sent) {
        this.sent = sent;
    }

    @Override
    public MainUnit action(AnswerText answerText, Message message) {
        BoxAnswer boxAnswer = answerText.getBoxAnswer().toBuilder().build();
        if (answerText.getInsert() != null) {
            List<String> words = answerText.getInsert().insert(message.getPersonId());
            String newMessage = InsertWords.insert(boxAnswer.getMessage(), words);
            boxAnswer.setMessage(newMessage);
        }

        if (answerText.getSent() != null) {
            answerText.getSent().send(message.getPersonId(), boxAnswer);
        } else {
            SendBox.sent(message, boxAnswer, sent);
        }

        return answerText;
    }


}

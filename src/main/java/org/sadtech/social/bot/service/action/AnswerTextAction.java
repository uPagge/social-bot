package org.sadtech.social.bot.service.action;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.sadtech.social.bot.domain.unit.AnswerText;
import org.sadtech.social.bot.domain.unit.MainUnit;
import org.sadtech.social.core.domain.BoxAnswer;
import org.sadtech.social.core.domain.content.Message;
import org.sadtech.social.core.service.sender.Sending;
import org.sadtech.social.core.utils.InsertWords;
import org.sadtech.social.core.utils.Sender;

import java.util.List;

/**
 * Обработчик Unit-а {@link AnswerText}.
 *
 * @author upagge [11/07/2019]
 */
@AllArgsConstructor
@NoArgsConstructor
public class AnswerTextAction implements ActionUnit<AnswerText, Message> {

    private Sending sending;

    @Override
    public MainUnit action(AnswerText answerText, Message message) {
        BoxAnswer boxAnswer = answerText.getBoxAnswer().toBuilder().build();
        if (answerText.getInsert() != null) {
            List<String> words = answerText.getInsert().insert(message.getPersonId());
            String newMessage = InsertWords.insert(boxAnswer.getMessage(), words);
            boxAnswer.setMessage(newMessage);
        }

        Sending answerTextSending = answerText.getSending();
        if (answerTextSending != null) {
            Sender.sends(message, boxAnswer, answerTextSending);
        } else {
            Sender.sends(message, boxAnswer, this.sending);
        }

        return answerText;
    }


}

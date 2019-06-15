package org.sadtech.bot.autoresponder.service.action;

import org.sadtech.bot.autoresponder.domain.unit.AnswerText;
import org.sadtech.bot.autoresponder.domain.unit.MainUnit;
import org.sadtech.bot.core.domain.BoxAnswer;
import org.sadtech.bot.core.domain.content.Message;
import org.sadtech.bot.core.service.sender.SendBox;
import org.sadtech.bot.core.service.sender.Sent;
import org.sadtech.bot.core.utils.InsertWords;

import java.util.List;

public class AnswerTextAction implements ActionUnit<AnswerText, Message> {

    private final Sent sent;

    public AnswerTextAction(Sent sent) {
        this.sent = sent;
    }

    @Override
    public MainUnit action(AnswerText answerText, Message message) {
        BoxAnswer boxAnswer = answerText.getBoxAnswer().prototype();
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

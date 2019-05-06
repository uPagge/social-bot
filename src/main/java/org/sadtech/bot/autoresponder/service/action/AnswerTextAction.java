package org.sadtech.bot.autoresponder.service.action;

import org.sadtech.bot.autoresponder.domain.unit.AnswerText;
import org.sadtech.bot.autoresponder.domain.unit.MainUnit;
import org.sadtech.bot.core.domain.BoxAnswer;
import org.sadtech.bot.core.insert.InsertWords;
import org.sadtech.bot.core.sender.Sent;

import java.util.List;

public class AnswerTextAction implements ActionUnit<AnswerText> {

    private final Sent sent;

    public AnswerTextAction(Sent sent) {
        this.sent = sent;
    }

    @Override
    public MainUnit action(AnswerText answerText, String message, Integer userId) {
        BoxAnswer boxAnswer = answerText.getBoxAnswer().clone();
        if (answerText.getInsert()!=null) {
            List<String> words = answerText.getInsert().insert(userId);
            String newMessage = InsertWords.insert(boxAnswer.getMessage(), words);
            boxAnswer.setMessage(newMessage);
        }
        sent.send(userId, boxAnswer);
        return answerText;
    }
}

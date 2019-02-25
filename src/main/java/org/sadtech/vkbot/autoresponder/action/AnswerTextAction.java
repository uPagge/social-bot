package org.sadtech.vkbot.autoresponder.action;

import org.sadtech.vkbot.autoresponder.domain.unit.AnswerText;
import org.sadtech.vkbot.core.sender.Sent;

import java.util.List;

public class AnswerTextAction implements ActionUnit<AnswerText> {

    private Sent sent;

    public AnswerTextAction(Sent sent) {
        this.sent = sent;
    }

    @Override
    public void action(AnswerText answerText, String message, Integer idPerson) {
        List<String> wordsProg = null;
        if (answerText.getInsert() != null) {
            wordsProg = answerText.getInsert().insert();
        }

        sent.send(idPerson, answerText.getBoxAnswer());
    }
}

package org.sadtech.bot.autoresponder.action;

import org.sadtech.bot.autoresponder.domain.unit.AnswerText;
import org.sadtech.bot.core.sender.Sent;

public class AnswerTextAction implements ActionUnit<AnswerText> {

    private final Sent sent;

    public AnswerTextAction(Sent sent) {
        this.sent = sent;
    }

    @Override
    public void action(AnswerText answerText, String message, Integer idPerson) {
        sent.send(idPerson, answerText.getBoxAnswer());
    }
}

package org.sadtech.vkbot.autoresponder.action.impl;

import org.apache.commons.collections4.CollectionUtils;
import org.sadtech.vkbot.autoresponder.action.ActionUnit;
import org.sadtech.vkbot.autoresponder.entity.unit.TextAnswer;
import org.sadtech.vkbot.core.sender.Sent;

import java.util.List;

public class TextAnswerAction implements ActionUnit<TextAnswer> {

    private Sent sent;

    public TextAnswerAction(Sent sent) {
        this.sent = sent;
    }

    @Override
    public void action(TextAnswer textAnswer, String message, Integer idPerson) {
        List<String> wordsProg = null;
        if (textAnswer.getInsert() != null) {
            wordsProg = textAnswer.getInsert().insert();
        }

        if (CollectionUtils.isNotEmpty(wordsProg)) {
            sent.send(idPerson, textAnswer.getBoxAnswer());
        } else {
            sent.send(idPerson, textAnswer.getBoxAnswer());
        }
    }
}

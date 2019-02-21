package org.sadtech.vkbot.autoresponder.action.impl;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.log4j.Logger;
import org.sadtech.autoresponder.entity.Unit;
import org.sadtech.vkbot.autoresponder.action.Action;
import org.sadtech.vkbot.autoresponder.action.ActionUnit;
import org.sadtech.vkbot.autoresponder.entity.unit.TextAnswer;
import org.sadtech.vkbot.core.sender.Sent;

import java.util.List;

public class TextAnswerAction implements ActionUnit {

    public static final Logger log = Logger.getLogger(TextAnswerAction.class);

    private Sent sent;

    public TextAnswerAction(Action generalActionUnit, Sent sent) {
        generalActionUnit.registerActionUnit(TextAnswer.class, this);
        this.sent = sent;
    }

    public TextAnswerAction(Sent sent) {
        this.sent = sent;
    }

    @Override
    public void action(Unit unit, String message, Integer idPerson) {
        TextAnswer textAnswer = (TextAnswer) unit;
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

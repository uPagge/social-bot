package org.sadtech.vkbot.autoresponder.action;

import org.sadtech.autoresponder.entity.Unit;
import org.sadtech.vkbot.autoresponder.entity.TextAnswer;
import org.sadtech.vkbot.core.sender.MailSandler;

public class TextAnswerAction implements ActionUnit {

    private MailSandler mailSandler;
    private GeneralActionUnit generalActionUnit;

    public TextAnswerAction(GeneralActionUnit generalActionUnit) {
        generalActionUnit.registerActionUnit(TextAnswer.class, this);
        this.mailSandler = generalActionUnit.getMailSandler();
    }

    @Override
    public void action(Unit unit) {
        TextAnswer textAnswer = (TextAnswer) unit;
        mailSandler.send(textAnswer.getAnswer());
    }
}

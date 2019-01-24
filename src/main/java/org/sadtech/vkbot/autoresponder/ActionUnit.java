package org.sadtech.vkbot.autoresponder;

import org.sadtech.vkbot.core.entity.unit.TextAndKeyBoardAnswer;
import org.sadtech.autoresponder.entity.Unit;
import org.sadtech.vkbot.core.sender.MailSanderVk;

public class ActionUnit {

    private Unit unit;
    private MailSanderVk mailSanderVk;

    public ActionUnit(MailSanderVk mailSanderVk) {
        this.mailSanderVk = mailSanderVk;
    }

    public ActionUnit(Unit unit, MailSanderVk mailSanderVk) {
        this.unit = unit;
        this.mailSanderVk = mailSanderVk;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public void action() {
        if (unit.getClass().equals(TextAndKeyBoardAnswer.class)) {
            sendMessage();
        }
    }

    private void sendMessage() {
        TextAndKeyBoardAnswer textAndKeyBoardAnswer = (TextAndKeyBoardAnswer) unit;
        mailSanderVk.sendTextandKeyBoard(textAndKeyBoardAnswer.getAnswer(), textAndKeyBoardAnswer.getKeyBoard());

    }

}

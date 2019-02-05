package org.sadtech.vkbot.autoresponder.action.impl;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.log4j.Logger;
import org.sadtech.autoresponder.entity.Unit;
import org.sadtech.vkbot.autoresponder.action.Action;
import org.sadtech.vkbot.autoresponder.action.ActionUnit;
import org.sadtech.vkbot.autoresponder.entity.TextAnswer;
import org.sadtech.vkbot.core.entity.Mail;
import org.sadtech.vkbot.core.entity.MailSend;
import org.sadtech.vkbot.core.sender.MailSandler;

import java.util.List;

public class TextAnswerAction implements ActionUnit {

    public static final Logger log = Logger.getLogger(TextAnswerAction.class);

    private MailSandler mailSandler;

    public TextAnswerAction(Action generalActionUnit) {
        generalActionUnit.registerActionUnit(TextAnswer.class, this);
        this.mailSandler = generalActionUnit.getMailSandler();
    }

    @Override
    public void action(Unit unit, Mail mail) {
        TextAnswer textAnswer = (TextAnswer) unit;
        List<String> wordsProg = null;
        if (textAnswer.getInsert() != null) {
            wordsProg = textAnswer.getInsert().insert();
        }
        MailSend mailSend = textAnswer.getMailSend();

        if (CollectionUtils.isNotEmpty(wordsProg)) {
            mailSandler.send(mailSend, mail.getPeerId(), mail.getPerson().getId(), wordsProg);
        } else {
            mailSandler.send(mailSend, mail.getPeerId(), mail.getPerson().getId());
        }
    }
}

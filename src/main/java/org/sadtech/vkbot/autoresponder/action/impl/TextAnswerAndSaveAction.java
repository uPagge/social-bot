package org.sadtech.vkbot.autoresponder.action.impl;

import org.apache.commons.collections4.CollectionUtils;
import org.sadtech.autoresponder.entity.Unit;
import org.sadtech.vkbot.autoresponder.action.Action;
import org.sadtech.vkbot.autoresponder.action.ActionUnit;
import org.sadtech.vkbot.autoresponder.entity.TextAnswerAndSave;
import org.sadtech.vkbot.core.entity.Mail;
import org.sadtech.vkbot.core.entity.MailSend;
import org.sadtech.vkbot.core.sender.MailSandler;

import java.util.List;

public class TextAnswerAndSaveAction implements ActionUnit {

    private MailSandler mailSandler;

    public TextAnswerAndSaveAction(Action generalActionUnit) {
        generalActionUnit.registerActionUnit(TextAnswerAndSave.class, this);
        this.mailSandler = generalActionUnit.getMailSandler();
    }


    @Override
    public void action(Unit unit, Mail mail) {
        TextAnswerAndSave textAnswerAndSave = (TextAnswerAndSave) unit;
        List list = null;
        if (textAnswerAndSave.getInsert() != null) {
            list = textAnswerAndSave.getInsert().insert();
        }
        if (CollectionUtils.isEmpty(textAnswerAndSave.getPrevUnits())) {
            textAnswerAndSave.getSaver().init(mail.getPerson().getId());
        } else {
            textAnswerAndSave.getSaver().save(mail.getPerson().getId(), textAnswerAndSave.getKey(), mail.getBody());
        }

        MailSend mailSend = textAnswerAndSave.getMailSend();
        if (CollectionUtils.isNotEmpty(list)) {
            mailSandler.send(mailSend, mail.getPerson().getId(), list);
        } else {
            mailSandler.send(mailSend, mail.getPerson().getId());
        }

        if (!checkNextSaveUnit(textAnswerAndSave)) {
            textAnswerAndSave.getSaver().push(mail.getPerson().getId());
        }
    }

    private boolean checkNextSaveUnit(TextAnswerAndSave unit) {
        if (unit.getNextUnits() == null) {
            return false;
        }
        for (Unit nextUnit : unit.getNextUnits()) {
            if (nextUnit.getClass().equals(TextAnswerAndSave.class)) {
                return true;
            }
        }
        return false;
    }
}

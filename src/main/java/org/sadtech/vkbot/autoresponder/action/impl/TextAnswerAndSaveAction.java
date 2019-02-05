package org.sadtech.vkbot.autoresponder.action.impl;

import org.apache.commons.collections4.CollectionUtils;
import org.sadtech.autoresponder.entity.Unit;
import org.sadtech.vkbot.autoresponder.action.Action;
import org.sadtech.vkbot.autoresponder.action.ActionUnit;
import org.sadtech.vkbot.autoresponder.entity.TextAnswerAndSave;
import org.sadtech.vkbot.autoresponder.saver.SaveStatus;
import org.sadtech.vkbot.core.entity.Mail;
import org.sadtech.vkbot.core.entity.MailSend;
import org.sadtech.vkbot.core.sender.MailSandler;

import java.util.List;
import java.util.Set;

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

        Set<SaveStatus> unitSaveStatus = textAnswerAndSave.getSaveStatuses();
        if (unitSaveStatus.contains(SaveStatus.INIT)) {
            textAnswerAndSave.getSavable().init(mail.getPerson().getId());
        }

        if (unitSaveStatus.contains(SaveStatus.SAVE)) {
            textAnswerAndSave.getSavable().save(mail.getPerson().getId(), textAnswerAndSave.getKey(), mail.getBody());
        }

        if (unitSaveStatus.contains(SaveStatus.FINISH)) {
            textAnswerAndSave.getSavable().push(mail.getPerson().getId());
        }


        MailSend mailSend = textAnswerAndSave.getMailSend();
        if (CollectionUtils.isNotEmpty(list)) {
            mailSandler.send(mailSend, mail.getPeerId(), mail.getPerson().getId(), list);
        } else {
            mailSandler.send(mailSend, mail.getPeerId(), mail.getPerson().getId());
        }
    }
}

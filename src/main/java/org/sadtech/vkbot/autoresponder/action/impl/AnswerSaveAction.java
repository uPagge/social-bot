package org.sadtech.vkbot.autoresponder.action.impl;

import org.sadtech.autoresponder.entity.Unit;
import org.sadtech.vkbot.autoresponder.action.Action;
import org.sadtech.vkbot.autoresponder.action.ActionUnit;
import org.sadtech.vkbot.autoresponder.entity.AnswerSave;
import org.sadtech.vkbot.autoresponder.saver.SaveStatus;
import org.sadtech.vkbot.core.entity.Mail;
import org.sadtech.vkbot.core.sender.MailSent;

import java.util.Set;

public class AnswerSaveAction implements ActionUnit {

    private MailSent mailSent;

    public AnswerSaveAction(Action generalActionUnit, MailSent mailSent) {
        generalActionUnit.registerActionUnit(AnswerSave.class, this);
        this.mailSent = mailSent;
    }

    public AnswerSaveAction(MailSent mailSent) {
        this.mailSent = mailSent;
    }

    @Override
    public void action(Unit unit, Mail mail) {
        AnswerSave answerSave = (AnswerSave) unit;

        Set<SaveStatus> unitSaveStatus = answerSave.getSaveStatuses();
        if (unitSaveStatus.contains(SaveStatus.INIT)) {
            answerSave.getSavable().init(mail.getPerson().getId());
        }

        if (unitSaveStatus.contains(SaveStatus.SAVE)) {
            answerSave.getSavable().save(mail.getPerson().getId(), answerSave.getKey(), mail.getBody());
        }

        if (unitSaveStatus.contains(SaveStatus.FINISH)) {
            answerSave.getSavable().push(mail.getPerson().getId());
        }
    }
}

package org.sadtech.vkbot.autoresponder.action;

import org.sadtech.autoresponder.entity.Unit;
import org.sadtech.vkbot.autoresponder.entity.BoxAnswer;
import org.sadtech.vkbot.autoresponder.entity.TextAnswerAndSave;
import org.sadtech.vkbot.core.entity.Mail;
import org.sadtech.vkbot.core.insert.InsertWords;
import org.sadtech.vkbot.core.sender.MailSandler;
import org.sadtech.vkbot.core.sender.MailSend;

public class TextAnswerAndSaveAction implements ActionUnit {

    private MailSandler mailSandler;

    public TextAnswerAndSaveAction(GeneralActionUnit generalActionUnit) {
        generalActionUnit.registerActionUnit(TextAnswerAndSave.class, this);
        this.mailSandler = generalActionUnit.getMailSandler();
    }


    @Override
    public void action(Unit unit, Mail mail) {
        TextAnswerAndSave textAnswerAndSave = (TextAnswerAndSave) unit;
        textAnswerAndSave.getBoxAnswer().setIdRecipient(mail.getPerson().getId());
        if (textAnswerAndSave.getInsert() != null) {
            textAnswerAndSave.getBoxAnswer().setInsertWords(textAnswerAndSave.getInsert().insert());
        }
        if (textAnswerAndSave.getPrevUnit() == null) {
            textAnswerAndSave.getSaver().init(mail.getPerson().getId());
        } else {
            textAnswerAndSave.getSaver().save(mail.getPerson().getId(), textAnswerAndSave.getKey(), mail.getBody());
        }

        MailSend mailSend = createMailSend(textAnswerAndSave.getBoxAnswer());
        mailSandler.send(mailSend);

        if (!checkNextSaveUnit(textAnswerAndSave)) {
            textAnswerAndSave.getSaver().push(mail.getPerson().getId());
        }

    }

    private MailSend createMailSend(BoxAnswer boxAnswer) {
        MailSend mailSend = new MailSend();
        if (boxAnswer.getInsertWords() != null) {
            InsertWords insertWords = new InsertWords();
            insertWords.setInText(boxAnswer.getMessage());
            insertWords.insert(boxAnswer.getInsertWords());
            mailSend.setMessage(insertWords.getOutText());
        } else {
            mailSend.setMessage(boxAnswer.getMessage());
        }
        mailSend.setIdRecipient(boxAnswer.getIdRecipient());
        mailSend.setKeyboard(boxAnswer.getKeyboard());
        mailSend.setStickerId(boxAnswer.getStickerId());
        mailSend.setaLong(boxAnswer.getaLong());
        mailSend.setLat(boxAnswer.getLat());
        return mailSend;
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

package org.sadtech.vkbot.autoresponder.action.impl;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.log4j.Logger;
import org.sadtech.autoresponder.entity.Unit;
import org.sadtech.vkbot.autoresponder.action.Action;
import org.sadtech.vkbot.autoresponder.action.ActionUnit;
import org.sadtech.vkbot.autoresponder.entity.BoxAnswer;
import org.sadtech.vkbot.autoresponder.entity.TextAnswer;
import org.sadtech.vkbot.core.entity.Mail;
import org.sadtech.vkbot.core.entity.MailSend;
import org.sadtech.vkbot.core.insert.InsertWords;
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
        textAnswer.getBoxAnswer().setIdRecipient(mail.getPerson().getId());
        if (textAnswer.getInsert() != null) {
            List<String> list = textAnswer.getInsert().insert();
            if (CollectionUtils.isNotEmpty(list)) {
                textAnswer.getBoxAnswer().setInsertWords(list);
            }
        }
        MailSend mailSend = createMailSend(textAnswer.getBoxAnswer());
        mailSandler.send(mailSend);
    }

    private MailSend createMailSend(BoxAnswer boxAnswer) {
        MailSend mailSend = new MailSend();
        if (boxAnswer.getInsertWords()!=null) {
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
}

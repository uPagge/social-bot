package org.sadtech.vkbot.autoresponder.action;

import org.apache.log4j.Logger;
import org.sadtech.autoresponder.entity.Unit;
import org.sadtech.vkbot.autoresponder.entity.TextAnswer;
import org.sadtech.vkbot.core.entity.Mail;
import org.sadtech.vkbot.core.sender.MailSandler;
import org.sadtech.vkbot.core.sender.MailSend;

public class TextAnswerAction implements ActionUnit {

    public static final Logger log = Logger.getLogger(TextAnswerAction.class);

    private MailSandler mailSandler;

    public TextAnswerAction(GeneralActionUnit generalActionUnit) {
        generalActionUnit.registerActionUnit(TextAnswer.class, this);
        this.mailSandler = generalActionUnit.getMailSandler();
    }

    @Override
    public void action(Unit unit, Mail mail) {
        TextAnswer textAnswer = (TextAnswer) unit;
        MailSend mailSend = new MailSend();
        mailSend.setIdRecipient(mail.getPerson().getId());
        if (textAnswer.getKeyBoard()!=null) {
            mailSend.setKeyboard(textAnswer.getKeyBoard().getKeyboard().toString());
        }
        if(textAnswer.getAnswer()!=null) {
            mailSend.setMessage(textAnswer.getAnswer());
        }
        mailSandler.send(mailSend);
    }
}

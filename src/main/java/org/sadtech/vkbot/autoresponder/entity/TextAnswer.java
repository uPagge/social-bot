package org.sadtech.vkbot.autoresponder.entity;

import org.sadtech.vkbot.autoresponder.entity.usercode.Insert;
import org.sadtech.vkbot.core.entity.MailSend;

public class TextAnswer extends MainUnit {

    private MailSend mailSend;
    private Insert insert;

    public TextAnswer() {
        mailSend = new MailSend();
    }

    public MailSend getMailSend() {
        return mailSend;
    }

    public void setMailSend(MailSend mailSend) {
        this.mailSend = mailSend;
    }

    public Insert getInsert() {
        return insert;
    }

    public void setInsert(Insert insert) {
        this.insert = insert;
    }
}

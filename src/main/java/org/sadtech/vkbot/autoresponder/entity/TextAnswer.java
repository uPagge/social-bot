package org.sadtech.vkbot.autoresponder.entity;

import org.sadtech.autoresponder.entity.Unit;
import org.sadtech.vkbot.core.sender.MailSend;

import java.util.List;
import java.util.Set;

public class TextAnswer extends Unit {

    private MailSend mailSend;

    public TextAnswer() {
        mailSend = new MailSend();
    }

    public TextAnswer(Set<String> keyWords, Integer matchThreshold, Integer priority, Boolean level, List<Unit> nextUnits, MailSend mailSend) {
        super(keyWords, matchThreshold, priority, level, nextUnits);
        this.mailSend = mailSend;
    }

    public MailSend getMailSend() {
        return mailSend;
    }

    public void setMailSend(MailSend mailSend) {
        this.mailSend = mailSend;
    }
}

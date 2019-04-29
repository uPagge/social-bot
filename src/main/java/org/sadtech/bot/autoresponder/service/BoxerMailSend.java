package org.sadtech.bot.autoresponder.service;

import org.sadtech.bot.core.domain.BoxAnswer;
import org.sadtech.bot.core.domain.MailSend;

public class BoxerMailSend {

    private BoxerMailSend() {
        throw new IllegalStateException("Utility Class");
    }

    public static MailSend create(BoxAnswer boxAnswer) {
        MailSend mailSend = new MailSend();
        mailSend.setMessage(boxAnswer.getMessage());
        mailSend.setKeyboard(boxAnswer.getKeyboard());
        mailSend.setStickerId(boxAnswer.getStickerId());
        mailSend.setLat(boxAnswer.getLat());
        mailSend.setaLong(boxAnswer.getaLong());
        return mailSend;
    }

}

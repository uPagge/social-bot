package org.sadtech.vkbot.autoresponder.service;

import org.sadtech.vkbot.core.entity.BoxAnswer;
import org.sadtech.vkbot.core.entity.MailSend;

public class BoxerMailSend {

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
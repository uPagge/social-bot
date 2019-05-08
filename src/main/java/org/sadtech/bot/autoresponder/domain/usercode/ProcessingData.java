package org.sadtech.bot.autoresponder.domain.usercode;

public interface ProcessingData {
    String processing(Integer userId, String mailUser);
}

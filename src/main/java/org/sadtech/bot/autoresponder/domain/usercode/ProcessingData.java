package org.sadtech.bot.autoresponder.domain.usercode;

public interface ProcessingData {
    String processing(Integer personId, String message);
}

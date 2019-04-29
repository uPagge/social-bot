package org.sadtech.bot.autoresponder.domain.usercode;

@FunctionalInterface
public interface ProcessingData {
    String processing(String mailUser);
}

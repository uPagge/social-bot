package org.sadtech.vkbot.autoresponder.domain.usercode;

@FunctionalInterface
public interface ProcessingData {
    String processing(String mailUser);
}

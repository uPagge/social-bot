package org.sadtech.vkbot.autoresponder.entity.usercode;

@FunctionalInterface
public interface ProcessingData {
    String processing(String mailUser);
}

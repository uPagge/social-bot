package org.sadtech.vkbot.autoresponder.saver;

public interface Saver {

    void init();
    void save(String key, String value);
    void push();

}

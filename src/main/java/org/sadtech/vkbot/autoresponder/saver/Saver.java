package org.sadtech.vkbot.autoresponder.saver;

public interface Saver {

    void init(Integer userId);
    void save(Integer userId, String key, String value);
    void push(Integer userId);

}

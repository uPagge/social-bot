package org.sadtech.vkbot.autoresponder.saver;

public interface Savable {

    void init(Integer userId);
    void save(Integer userId, String key, String value);
    void push(Integer userId);

}

package org.sadtech.bot.autoresponder.saver;

public interface Savable {

    void init(Integer personId);

    void save(Integer personId, String key, String value);

    void push(Integer personId);

}

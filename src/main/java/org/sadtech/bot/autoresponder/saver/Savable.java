package org.sadtech.bot.autoresponder.saver;

public interface Savable<S> {

    void init(Integer personId);

    void save(Integer personId, S save);

    void push(Integer personId);

}

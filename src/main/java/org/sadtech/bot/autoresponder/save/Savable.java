package org.sadtech.bot.autoresponder.save;

public interface Savable<S> {

    void init(Integer personId);

    void save(Integer personId, S save);

    void push(Integer personId);

}

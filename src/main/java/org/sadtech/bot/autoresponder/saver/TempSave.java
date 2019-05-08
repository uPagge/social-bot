package org.sadtech.bot.autoresponder.saver;

public interface TempSave {

    void save(Integer userId, String message);

    String load(Integer userId);

}

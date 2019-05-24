package org.sadtech.bot.autoresponder.save;

public interface TempSave {

    void save(Integer userId, String message);

    String load(Integer userId);

}

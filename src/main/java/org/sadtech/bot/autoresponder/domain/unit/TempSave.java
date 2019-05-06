package org.sadtech.bot.autoresponder.domain.unit;

public interface TempSave {

    void save(Integer userId, String message);

    String load(Integer userId);

}

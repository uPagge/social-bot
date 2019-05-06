package org.sadtech.bot.autoresponder.domain.unit;

import java.util.HashMap;
import java.util.Map;

public class TempSaveImpl implements TempSave {

    private Map<Integer, String> saveMap = new HashMap<>();

    @Override
    public void save(Integer userId, String message) {
        saveMap.put(userId, message);
    }

    @Override
    public String load(Integer userId) {
        return saveMap.get(userId);
    }
}

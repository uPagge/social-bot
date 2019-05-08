package org.sadtech.bot.autoresponder.saver;

import java.util.HashMap;
import java.util.Map;

public class TempSaveImpl implements TempSave {

    private final Map<Integer, String> saveMap = new HashMap<>();

    @Override
    public void save(Integer personId, String message) {
        saveMap.put(personId, message);
    }

    @Override
    public String load(Integer personId) {
        return saveMap.get(personId);
    }
}

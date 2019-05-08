package org.sadtech.bot.autoresponder.saver;

import java.util.HashMap;
import java.util.Map;

public abstract class LocalSavable implements Savable {

    final Map<Integer, Map<String, String>> map = new HashMap<>();
    String nameForm;

    @Override
    public void init(Integer userId) {
        map.put(userId, new HashMap<>());
    }

    @Override
    public void save(Integer userId, String key, String value) {
        map.get(userId).put(key, value);
    }

    public String getNameForm() {
        return nameForm;
    }

    public void setNameForm(String nameForm) {
        this.nameForm = nameForm;
    }
}

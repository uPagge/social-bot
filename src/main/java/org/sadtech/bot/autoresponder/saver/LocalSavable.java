package org.sadtech.bot.autoresponder.saver;

import java.util.HashMap;
import java.util.Map;

public abstract class LocalSavable implements Savable {

    final Map<Integer, Map<String, String>> map = new HashMap<>();
    String nameForm;

    @Override
    public void init(Integer personId) {
        map.put(personId, new HashMap<>());
    }

    @Override
    public void save(Integer personId, String key, String value) {
        map.get(personId).put(key, value);
    }

    public String getNameForm() {
        return nameForm;
    }

    public void setNameForm(String nameForm) {
        this.nameForm = nameForm;
    }
}

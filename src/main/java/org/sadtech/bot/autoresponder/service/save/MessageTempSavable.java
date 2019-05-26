package org.sadtech.bot.autoresponder.service.save;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessageTempSavable implements Savable<String> {

    private final Map<Integer, String> message = new HashMap<>();

    @Override
    public void init(Integer personId) {
        // Нет нужды
    }

    @Override
    public void save(Integer personId, String save) {
        message.put(personId, save);
    }

    @Override
    public void push(Integer personId) {
        // нет нужды
    }

    @Override
    public List<String> download(Integer personId) {
        return Collections.singletonList(message.get(personId));
    }

    @Override
    public String getLastElement(Integer personId) {
        return message.get(personId);
    }

}

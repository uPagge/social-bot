package org.sadtech.social.bot.service.save;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Хранилище для временных ответов пользователей.
 *
 * @author upagge [11/07/2019]
 */
public class MessageTempPreservable implements Preservable<String> {

    private final Map<Long, String> message = new HashMap<>();

    @Override
    public void save(Long personId, String save) {
        message.put(personId, save);
    }

    @Override
    public List<String> getAllSaveElement(Long personId) {
        return Collections.singletonList(message.get(personId));
    }

    @Override
    public String getLastSaveElement(Long personId) {
        return message.get(personId);
    }

}

package org.sadtech.social.bot.service.save;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class LocalPreservable<S> implements Preservable<S> {

    private final Map<Long, Map<String, S>> saveMap = new HashMap<>();

    @Override
    public void save(Long personId, String key, S save) {
        if (!saveMap.containsKey(personId)) {
            saveMap.put(personId, new HashMap<>());
        }
        saveMap.get(personId).put(key, save);
    }

    @Override
    public Optional<S> getByKey(Long personId, String key) {
        if (saveMap.containsKey(personId)) {
            if (saveMap.get(personId).containsKey(key)) {
                return Optional.of(saveMap.get(personId).get(key));
            }
        }
        return Optional.empty();
    }

    @Override
    public Map<String, S> getAllSaveElement(Long personId) {
        return null;
    }


}

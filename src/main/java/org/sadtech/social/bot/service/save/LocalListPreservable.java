package org.sadtech.social.bot.service.save;

import org.sadtech.social.core.exception.NotFoundException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


public class LocalListPreservable<S> implements Preservable<S> {

    private final Map<Long, List<S>> saveMap = new HashMap<>();

    @Override
    public void save(Long personId, S save) {
        if (!saveMap.containsKey(personId)) {
            saveMap.put(personId, new ArrayList<>());
        }
        saveMap.get(personId).add(save);
    }

    @Override
    public List<S> getAllSaveElement(Long personId) {
        return saveMap.get(personId);
    }

    @Override
    public S getLastSaveElement(Long personId) {
        return Optional
                .ofNullable(saveMap.get(personId).get(saveMap.get(personId).size() - 1))
                .orElseThrow(() -> new NotFoundException("Пользователь не найден"));
    }

}

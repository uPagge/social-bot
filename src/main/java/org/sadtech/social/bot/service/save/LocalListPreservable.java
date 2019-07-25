package org.sadtech.social.bot.service.save;

import org.sadtech.social.core.exception.NotFoundException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


public class LocalListPreservable<S> implements Preservable<S> {

    private final Map<Integer, List<S>> saveMap = new HashMap<>();

    @Override
    public void init(Integer personId) {
        saveMap.put(personId, new ArrayList<>());
    }

    @Override
    public void save(Integer personId, S save) {
        Optional.ofNullable(saveMap.get(personId))
                .orElseThrow(() -> new NotFoundException(490, "Пользователь не найден"))
                .add(save);
    }

    @Override
    public List<S> getAllSaveElement(Integer personId) {
        return saveMap.get(personId);
    }

    @Override
    public S getLastSaveElement(Integer personId) {
        return Optional
                .ofNullable(saveMap.get(personId).get(saveMap.get(personId).size() - 1))
                .orElseThrow(() -> new NotFoundException(490, "Пользователь не найден"));
    }
}

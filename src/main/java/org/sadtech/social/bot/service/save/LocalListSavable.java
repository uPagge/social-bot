package org.sadtech.social.bot.service.save;

import org.sadtech.social.core.exception.NotFoundException;
import org.sadtech.social.core.service.sender.Sent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


public abstract class LocalListSavable<S> implements Savable<S> {

    protected final Map<Integer, List<S>> saveMap = new HashMap<>();
    protected String nameForm;
    protected Sent sent;

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
    public List<S> download(Integer personId) {
        return saveMap.get(personId);
    }

    @Override
    public S getLastElement(Integer personId) {
        return Optional
                .ofNullable(saveMap.get(personId).get(saveMap.get(personId).size()-1))
                .orElseThrow(() -> new NotFoundException(490, "Пользователь не найден"));
    }

    public String getNameForm() {
        return nameForm;
    }

    public void setNameForm(String nameForm) {
        this.nameForm = nameForm;
    }

    public Sent getSent() {
        return sent;
    }

    public void setSent(Sent sent) {
        this.sent = sent;
    }
}

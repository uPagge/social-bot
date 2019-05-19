package org.sadtech.bot.autoresponder.saver;

import org.sadtech.bot.core.sender.Sent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class LocalSavable<S> implements Savable<S> {

    protected final Map<Integer, List<S>> saveMap = new HashMap<>();
    protected String nameForm;
    protected Sent sent;

    @Override
    public void init(Integer personId) {
        saveMap.put(personId, new ArrayList<>());
    }

    @Override
    public void save(Integer personId, S save) {
        saveMap.get(personId).add(save);
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

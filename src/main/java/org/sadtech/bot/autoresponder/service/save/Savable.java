package org.sadtech.bot.autoresponder.service.save;

import java.util.List;

public interface Savable<S> {

    void init(Integer personId);

    void save(Integer personId, S save);

    void push(Integer personId);

    List<S> download(Integer personId);

    S getLastElement(Integer personId);

}

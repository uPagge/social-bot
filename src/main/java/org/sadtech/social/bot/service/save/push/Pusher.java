package org.sadtech.social.bot.service.save.push;

import java.util.Map;

/**
 * TODO: Добавить описание класса.
 *
 * @author upagge [13/07/2019]
 */
@FunctionalInterface
public interface Pusher<D> {

    void push(Map<String, D> saveElement);

}

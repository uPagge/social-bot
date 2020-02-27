package org.sadtech.social.bot.service.save;

import org.sadtech.social.bot.service.save.push.Pusher;

import java.util.Map;
import java.util.Optional;

/**
 * Интерфейс для сохранения и взаимодейтсвия с ответами прав пользователя.
 *
 * @author upagge [11/07/2019]
 */
public interface Preservable<S> {

    /**
     * Сохранение данных для пользователя
     *
     * @param personId Идентификатор пользователя
     * @param save     Объект данных
     */
    void save(Long personId, String key, S save);

    Optional<S> getByKey(Long personId, String key);

    /**
     * Финальное сохранение, можно реализовать как отправку данных куда-то
     *
     * @param personId Идентификатор пользователя
     */
    default void push(Long personId, Pusher<S> pusher) {
        Optional.ofNullable(pusher).ifPresent(sPusher -> sPusher.push(getAllSaveElement(personId)));
    }

    Map<String, S> getAllSaveElement(Long personId);


}

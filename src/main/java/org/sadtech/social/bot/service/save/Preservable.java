package org.sadtech.social.bot.service.save;

import org.sadtech.social.bot.service.save.push.Pusher;

import java.util.List;
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
    void save(Long personId, S save);

    /**
     * Финальное сохранение, можно реализовать как отправку данных куда-то
     *
     * @param personId Идентификатор пользователя
     */
    default void push(Long personId, Pusher<S> pusher) {
        Optional.ofNullable(pusher).ifPresent(sPusher -> sPusher.push(getAllSaveElement(personId)));
    }

    /**
     * Загрузить все сохраненные данные для пользователя
     *
     * @param personId Идентификатор пользователя
     * @return Список данных
     */
    List<S> getAllSaveElement(Long personId);

    /**
     * Отдать последний сохраненный объект для пользователя
     *
     * @param personId Идентификатор пользователя
     * @return Объект сохраненных данных
     */
    S getLastSaveElement(Long personId);

}

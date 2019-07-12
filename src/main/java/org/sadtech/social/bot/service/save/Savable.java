package org.sadtech.social.bot.service.save;

import org.sadtech.social.core.exception.ConfigAppException;

import java.util.List;

/**
 * Интерфейс для сохранения и взаимодейтсвия с ответами прав пользователя.
 *
 * @author upagge [11/07/2019]
 */
public interface Savable<S> {

    /**
     * Инициализация места в хранилище под пользователя
     *
     * @param personId Идентификатор пользователя
     */
    default void init(Integer personId) {
        throw new ConfigAppException(945, "Метод init не реализован");
    }

    /**
     * Сохранение данных для пользователя
     *
     * @param personId Идентификатор пользователя
     * @param save     Объект данных
     */
    void save(Integer personId, S save);

    /**
     * Финальное сохранение, можно реализовать как отправку данных куда-то
     *
     * @param personId Идентификатор пользователя
     */
    default void push(Integer personId) {
        throw new ConfigAppException(945, "Метод push не реализован");
    }

    /**
     * Загрузить все сохраненные данные для пользователя
     *
     * @param personId Идентификатор пользователя
     * @return Список данных
     */
    List<S> download(Integer personId);

    /**
     * Отдать последний сохраненный объект для пользователя
     *
     * @param personId Идентификатор пользователя
     * @return Объект сохраненных данных
     */
    S getLastElement(Integer personId);

}

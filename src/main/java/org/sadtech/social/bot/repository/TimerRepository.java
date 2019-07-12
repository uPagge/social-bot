package org.sadtech.social.bot.repository;

import org.sadtech.social.bot.domain.Timer;

import java.time.LocalDateTime;
import java.util.Collection;

/**
 * Репозиторий для работы с хранилищем таймеров {@link Timer}.
 *
 * @author upagge [11/07/2019]
 */
public interface TimerRepository {

    Integer add(Timer timer);

    /**
     * Получить все таймеры, время активации которых меньше, чем переданное время
     *
     * @param time Время
     * @return Коллекция таймеров
     */
    Collection<Timer> getTimerActive(LocalDateTime time);

    void remove(Integer id);

    void edit(Timer timer);

}

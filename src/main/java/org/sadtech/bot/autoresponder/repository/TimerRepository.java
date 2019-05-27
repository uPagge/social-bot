package org.sadtech.bot.autoresponder.repository;

import org.sadtech.bot.autoresponder.domain.Timer;

import java.time.LocalDateTime;
import java.util.Collection;

public interface TimerRepository {

    Integer add(Timer timer);

    Collection<Timer> getTimerActive(LocalDateTime time);

    void remove(Integer id);

    void edit(Timer timer);

}

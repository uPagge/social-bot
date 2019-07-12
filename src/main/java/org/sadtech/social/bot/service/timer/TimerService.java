package org.sadtech.social.bot.service.timer;

import org.sadtech.social.bot.domain.Timer;

import java.util.List;

public interface TimerService {

    List<Timer> getTimerActive();

    Integer add(Timer timer);

    void remove(Integer id);

    void edit(Integer id, Timer timer);

}

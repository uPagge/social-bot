package org.sadtech.bot.autoresponder.service.timer;

import org.sadtech.bot.autoresponder.domain.Timer;

import java.time.LocalDateTime;
import java.util.List;

public interface TimerService {

    List<Timer> getTimerActive(LocalDateTime time);

    Integer add(Timer timer);

    void remove(Integer id);

    void edit(Integer id, Timer timer);

}

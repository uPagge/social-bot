package org.sadtech.bot.autoresponder.timer;

import java.time.LocalDateTime;
import java.util.List;

public interface TimerActionService {

    List<TimerAction> getTimerActive(LocalDateTime time);

    void add(TimerAction timerAction);

    void remove(TimerAction timerAction);

}

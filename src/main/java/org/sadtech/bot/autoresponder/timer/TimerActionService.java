package org.sadtech.bot.autoresponder.timer;

import java.util.List;

public interface TimerActionService {

    List<TimerAction> getTimerActive(Long time);

    void add(TimerAction timerAction);

    void remove(TimerAction timerAction);

}

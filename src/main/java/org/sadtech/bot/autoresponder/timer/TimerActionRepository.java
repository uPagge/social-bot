package org.sadtech.bot.autoresponder.timer;

import java.util.List;

public interface TimerActionRepository {

    void add(TimerAction timerAction);

    List<TimerAction> getTimerActionList();

    void remove(TimerAction timerAction);

}

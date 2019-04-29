package org.sadtech.bot.autoresponder.timer;

import java.util.List;

public interface TimerActionRepository {

    void add(TimerAction timerAction);

    List<TimerAction> getTimerActionList();

    void setTimerActionList(List<TimerAction> timerActionList);

    void remove(TimerAction timerAction);

}

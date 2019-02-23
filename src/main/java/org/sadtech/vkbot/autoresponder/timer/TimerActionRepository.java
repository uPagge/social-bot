package org.sadtech.vkbot.autoresponder.timer;

import java.util.List;

public interface TimerActionRepository {

    void add(TimerAction timerAction);

    List<TimerAction> getTimerActionList();

    void setTimerActionList(List<TimerAction> timerActionList);

    void remove(TimerAction timerAction);

}

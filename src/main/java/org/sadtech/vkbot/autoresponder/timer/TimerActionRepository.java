package org.sadtech.vkbot.autoresponder.timer;

import org.sadtech.vkbot.autoresponder.timer.impl.TimerAction;

import java.util.List;

public interface TimerActionRepository {

    void add(TimerAction timerAction);

    List<TimerAction> getTimerActionList();

    void setTimerActionList(List<TimerAction> timerActionList);

    void remove(TimerAction timerAction);

}

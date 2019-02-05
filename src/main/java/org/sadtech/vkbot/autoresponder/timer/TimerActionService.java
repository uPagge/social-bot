package org.sadtech.vkbot.autoresponder.timer;

import org.sadtech.vkbot.autoresponder.timer.impl.TimerAction;

import java.util.List;

public interface TimerActionService {

    List<TimerAction> getTimerActive(Long time);

    void add(TimerAction timerAction);

    void remove(TimerAction timerAction);

}

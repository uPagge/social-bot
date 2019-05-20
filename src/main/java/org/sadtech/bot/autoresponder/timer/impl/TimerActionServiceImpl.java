package org.sadtech.bot.autoresponder.timer.impl;

import org.sadtech.bot.autoresponder.timer.TimerAction;
import org.sadtech.bot.autoresponder.timer.TimerActionRepository;
import org.sadtech.bot.autoresponder.timer.TimerActionService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TimerActionServiceImpl implements TimerActionService {

    private final TimerActionRepository timerActionRepositoryList;

    public TimerActionServiceImpl(TimerActionRepositoryList timerActionRepositoryList) {
        this.timerActionRepositoryList = timerActionRepositoryList;
    }

    public TimerActionRepository getTimerActionRepositoryList() {
        return timerActionRepositoryList;
    }

    public List<TimerAction> getTimerActive(LocalDateTime time) {
        List<TimerAction> timerActions = new ArrayList<>();
        for (TimerAction timerAction : timerActionRepositoryList.getTimerActionList()) {
            if (timerAction.getTimeActive().isAfter(time)) {
                timerActions.add(timerAction);
            }
        }
        return timerActions;
    }

    public void add(TimerAction timerAction) {
        timerActionRepositoryList.add(timerAction);
    }

    public void remove(TimerAction timerAction) {
        timerActionRepositoryList.remove(timerAction);
    }

}

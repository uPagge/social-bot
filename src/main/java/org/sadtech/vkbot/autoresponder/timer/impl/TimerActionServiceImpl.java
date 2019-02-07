package org.sadtech.vkbot.autoresponder.timer.impl;

import org.sadtech.vkbot.autoresponder.timer.TimerActionRepository;
import org.sadtech.vkbot.autoresponder.timer.TimerActionService;

import java.util.ArrayList;
import java.util.List;

public class TimerActionServiceImpl implements TimerActionService {

    private TimerActionRepository timerActionRepositoryList;

    public TimerActionServiceImpl(TimerActionRepositoryList timerActionRepositoryList) {
        this.timerActionRepositoryList = timerActionRepositoryList;
    }

    public TimerActionRepository getTimerActionRepositoryList() {
        return timerActionRepositoryList;
    }

    public void setTimerActionRepositoryList(TimerActionRepository timerActionRepositoryList) {
        this.timerActionRepositoryList = timerActionRepositoryList;
    }

    public List<TimerAction> getTimerActive(Long time) {
        List<TimerAction> timerActions = new ArrayList<>();
        for (TimerAction timerAction : timerActionRepositoryList.getTimerActionList()) {
            if (timerAction.getTimeActive() <= time) {
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

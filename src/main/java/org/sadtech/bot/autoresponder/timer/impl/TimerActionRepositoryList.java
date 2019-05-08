package org.sadtech.bot.autoresponder.timer.impl;

import org.sadtech.bot.autoresponder.timer.TimerAction;
import org.sadtech.bot.autoresponder.timer.TimerActionRepository;

import java.util.ArrayList;
import java.util.List;

public class TimerActionRepositoryList implements TimerActionRepository {

    private List<TimerAction> timerActionList = new ArrayList<>();

    public void add(TimerAction timerAction) {
        timerActionList.add(timerAction);
    }

    public List<TimerAction> getTimerActionList() {
        return timerActionList;
    }

    public void setTimerActionList(List<TimerAction> timerActionList) {
        this.timerActionList = timerActionList;
    }

    public void remove(TimerAction timerAction) {
        timerActionList.remove(timerAction);
    }

}

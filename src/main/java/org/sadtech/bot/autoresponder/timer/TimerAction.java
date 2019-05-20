package org.sadtech.bot.autoresponder.timer;

import org.sadtech.bot.autoresponder.domain.unit.MainUnit;

import java.time.LocalDateTime;

public class TimerAction {

    private MainUnit unit;
    private Integer personId;
    private LocalDateTime timeActive;

    public MainUnit getUnit() {
        return unit;
    }

    public void setUnit(MainUnit unit) {
        this.unit = unit;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public LocalDateTime getTimeActive() {
        return timeActive;
    }

    public void setTimeActive(LocalDateTime timeActive) {
        this.timeActive = timeActive;
    }

    @Override
    public String toString() {
        return "TimerAction{" +
                "personId=" + personId +
                ", timeActive=" + timeActive +
                '}';
    }

}

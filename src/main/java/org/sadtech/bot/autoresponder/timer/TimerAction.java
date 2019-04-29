package org.sadtech.bot.autoresponder.timer;

import org.sadtech.bot.autoresponder.domain.unit.MainUnit;

public class TimerAction {

    private MainUnit unit;
    private Integer idPerson;
    private Long timeActive;

    public MainUnit getUnit() {
        return unit;
    }

    public void setUnit(MainUnit unit) {
        this.unit = unit;
    }

    public Integer getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(Integer idPerson) {
        this.idPerson = idPerson;
    }

    public Long getTimeActive() {
        return timeActive;
    }

    public void setTimeActive(Long timeActive) {
        this.timeActive = timeActive;
    }

    @Override
    public String toString() {
        return "TimerAction{" +
                "idPerson=" + idPerson +
                ", timeActive=" + timeActive +
                '}';
    }

}

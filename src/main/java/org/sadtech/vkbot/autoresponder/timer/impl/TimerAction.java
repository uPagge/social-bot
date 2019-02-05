package org.sadtech.vkbot.autoresponder.timer.impl;

import org.sadtech.autoresponder.entity.Unit;
import org.sadtech.vkbot.autoresponder.action.ActionUnit;

public class TimerAction {

    private Unit unit;
    private ActionUnit actionUnit;
    private Integer idPerson;
    private Long timeActive;
    private Long period;
    private Boolean repeated = false;

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public ActionUnit getActionUnit() {
        return actionUnit;
    }

    public void setActionUnit(ActionUnit actionUnit) {
        this.actionUnit = actionUnit;
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

    public Boolean getRepeated() {
        return repeated;
    }

    public void setRepeated(Boolean repeated) {
        this.repeated = repeated;
    }

    public Long getPeriod() {
        return period;
    }

    public void setPeriod(Long period) {
        this.period = period;
    }

    @Override
    public String toString() {
        return "TimerAction{" +
                "idPerson=" + idPerson +
                ", timeActive=" + timeActive +
                ", period=" + period +
                ", repeated=" + repeated +
                '}';
    }
}

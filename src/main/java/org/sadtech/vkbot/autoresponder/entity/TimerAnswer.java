package org.sadtech.vkbot.autoresponder.entity;

import org.sadtech.autoresponder.entity.Unit;

public class TimerAnswer extends Unit {

    private Unit unit;
    private Long timeDelaySec;
    private Integer idUser;

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public Long getTimeDelaySec() {
        return timeDelaySec;
    }

    public void setTimeDelaySec(Long timeDelaySec) {
        this.timeDelaySec = timeDelaySec;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }
}

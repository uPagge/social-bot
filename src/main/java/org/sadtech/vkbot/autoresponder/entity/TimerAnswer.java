package org.sadtech.vkbot.autoresponder.entity;

import org.sadtech.autoresponder.entity.Unit;

public class TimerAnswer extends Unit {

    private Unit unit;
    private Integer idPerson;

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public Integer getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(Integer idPerson) {
        this.idPerson = idPerson;
    }
}

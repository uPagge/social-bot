package org.sadtech.vkbot.autoresponder.entity;

import org.sadtech.autoresponder.entity.Unit;

public abstract class MainUnit extends Unit {

    protected UnitActivStatus unitActivStatus = UnitActivStatus.DEFAULT;

    public UnitActivStatus getUnitActivStatus() {
        return unitActivStatus;
    }

    public void setUnitActivStatus(UnitActivStatus unitActivStatus) {
        this.unitActivStatus = unitActivStatus;
    }
}

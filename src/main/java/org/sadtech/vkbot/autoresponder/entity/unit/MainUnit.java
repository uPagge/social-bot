package org.sadtech.vkbot.autoresponder.entity.unit;

import org.sadtech.autoresponder.entity.Unit;

import java.util.Objects;

public abstract class MainUnit extends Unit {

    protected UnitActiveStatus unitActiveStatus = UnitActiveStatus.DEFAULT;

    public UnitActiveStatus getUnitActiveStatus() {
        return unitActiveStatus;
    }

    public void setUnitActiveStatus(UnitActiveStatus unitActiveStatus) {
        this.unitActiveStatus = unitActiveStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        MainUnit mainUnit = (MainUnit) o;
        return unitActiveStatus == mainUnit.unitActiveStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), unitActiveStatus);
    }
}

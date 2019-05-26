package org.sadtech.bot.autoresponder.domain.unit;

import org.sadtech.autoresponder.entity.Unit;

import java.util.Objects;

public abstract class MainUnit extends Unit {

    protected UnitActiveStatus activeStatus = UnitActiveStatus.DEFAULT;
    protected TypeUnit typeUnit;

    public TypeUnit getTypeUnit() {
        return typeUnit;
    }

    public UnitActiveStatus getActiveStatus() {
        return activeStatus;
    }

    public void setActiveStatus(UnitActiveStatus activeStatus) {
        this.activeStatus = activeStatus;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        MainUnit mainUnit = (MainUnit) o;
        return activeStatus == mainUnit.activeStatus &&
                typeUnit == mainUnit.typeUnit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), activeStatus, typeUnit);
    }
}

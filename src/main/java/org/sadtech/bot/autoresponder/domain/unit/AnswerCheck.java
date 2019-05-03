package org.sadtech.bot.autoresponder.domain.unit;

import org.sadtech.bot.autoresponder.domain.usercode.CheckData;

import java.util.Objects;

public class AnswerCheck extends MainUnit {

    private MainUnit unitTrue;
    private MainUnit unitFalse;
    private CheckData check;

    public AnswerCheck() {
        super();
        typeUnit = TypeUnit.CHECK;
    }

    public MainUnit getUnitTrue() {
        return unitTrue;
    }

    public void setUnitTrue(MainUnit unitTrue) {
        this.unitTrue = unitTrue;
    }

    public MainUnit getUnitFalse() {
        return unitFalse;
    }

    public void setUnitFalse(MainUnit unitFalse) {
        this.unitFalse = unitFalse;
    }

    public CheckData getCheck() {
        return check;
    }

    public void setCheck(CheckData check) {
        this.check = check;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        AnswerCheck that = (AnswerCheck) o;
        return Objects.equals(unitTrue, that.unitTrue) &&
                Objects.equals(unitFalse, that.unitFalse) &&
                Objects.equals(check, that.check);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), unitTrue, unitFalse, check);
    }
}

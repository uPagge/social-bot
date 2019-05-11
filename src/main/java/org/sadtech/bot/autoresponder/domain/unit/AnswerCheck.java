package org.sadtech.bot.autoresponder.domain.unit;

import org.sadtech.bot.autoresponder.domain.usercode.CheckData;

import java.util.Objects;

public class AnswerCheck extends MainUnit {

    private final MainUnit unitTrue;
    private final MainUnit unitFalse;
    private final CheckData check;

    public AnswerCheck(MainUnit unitTrue, MainUnit unitFalse, CheckData check) {
        this.unitTrue = unitTrue;
        this.unitFalse = unitFalse;
        this.check = check;
        typeUnit = TypeUnit.CHECK;
    }

    public MainUnit getUnitTrue() {
        return unitTrue;
    }

    public MainUnit getUnitFalse() {
        return unitFalse;
    }

    public CheckData getCheck() {
        return check;
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

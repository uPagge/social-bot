package org.sadtech.bot.autoresponder.domain.unit;

import org.sadtech.bot.autoresponder.service.usercode.CheckData;

import java.util.Objects;

public class AnswerCheck extends MainUnit {

    private MainUnit unitTrue;
    private MainUnit unitFalse;
    private CheckData check;

    private AnswerCheck() {
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

    public static Bulder bulder() {
        return new AnswerCheck().new Bulder();
    }

    public class Bulder {
        private Bulder() {

        }

        public Bulder unitTrue(MainUnit unitTrue) {
            AnswerCheck.this.unitTrue = unitTrue;
            return this;
        }

        public Bulder unitFalse(MainUnit unitFalse) {
            AnswerCheck.this.unitFalse = unitFalse;
            return this;
        }

        public Bulder check(CheckData checkData) {
            AnswerCheck.this.check = checkData;
            return this;
        }

        public AnswerCheck build() {
            return AnswerCheck.this;
        }
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

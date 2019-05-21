package org.sadtech.bot.autoresponder.domain.unit;

import org.sadtech.bot.autoresponder.domain.usercode.TestInsert;
import org.sadtech.bot.autoresponder.saver.TempSave;
import org.sadtech.bot.autoresponder.service.action.AnswerValidityAction;

import java.util.Objects;

public class AnswerValidity extends MainUnit {

    private MainUnit unitYes;
    private MainUnit unitNo;
    private MainUnit unitNull;
    private TempSave tempSave;
    private TestInsert testInsert;

    private AnswerValidity() {
        typeUnit = TypeUnit.VALIDITY;
    }

    public MainUnit getUnitYes() {
        return unitYes;
    }

    public MainUnit getUnitNo() {
        return unitNo;
    }

    public TempSave getTempSave() {
        return tempSave;
    }

    public TestInsert getTestInsert() {
        return testInsert;
    }

    public MainUnit getUnitNull() {
        return unitNull;
    }

    public static Builder builder() {
        return new AnswerValidity().new Builder();
    }

    public class Builder {

        private Builder() {

        }

        public Builder unitYes(MainUnit unitYes) {
            AnswerValidity.this.unitYes = unitYes;
            AnswerValidity.this.setKeyWords(AnswerValidityAction.WORDS_YES);
            return this;
        }

        public Builder unitNo(MainUnit unitNo) {
            AnswerValidity.this.unitNo = unitNo;
            AnswerValidity.this.setKeyWords(AnswerValidityAction.WORDS_NO);
            return this;
        }

        public Builder unitNull(MainUnit unitNull) {
            AnswerValidity.this.unitNull = unitNull;
            return this;
        }

        public Builder tempSave(TempSave tempSave) {
            AnswerValidity.this.tempSave = tempSave;
            return this;
        }

        public Builder testInsert(TestInsert testInsert) {
            AnswerValidity.this.testInsert = testInsert;
            return this;
        }

        public AnswerValidity build() {
            return AnswerValidity.this;
        }


    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AnswerValidity)) return false;
        if (!super.equals(o)) return false;
        AnswerValidity that = (AnswerValidity) o;
        return Objects.equals(unitYes, that.unitYes) &&
                Objects.equals(unitNo, that.unitNo) &&
                Objects.equals(unitNull, that.unitNull) &&
                Objects.equals(tempSave, that.tempSave) &&
                Objects.equals(testInsert, that.testInsert);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), unitYes, unitNo, unitNull, tempSave, testInsert);
    }
}

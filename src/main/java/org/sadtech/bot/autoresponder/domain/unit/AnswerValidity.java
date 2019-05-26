package org.sadtech.bot.autoresponder.domain.unit;

import org.sadtech.bot.autoresponder.service.action.AnswerValidityAction;
import org.sadtech.bot.autoresponder.service.save.Savable;
import org.sadtech.bot.autoresponder.service.usercode.PairInsert;

import java.util.Objects;

public class AnswerValidity extends MainUnit {

    private MainUnit unitYes;
    private MainUnit unitNo;
    private MainUnit unitNull;
    private Savable<String> tempSave;
    private PairInsert pairInsert;

    private AnswerValidity() {
        typeUnit = TypeUnit.VALIDITY;
    }

    public MainUnit getUnitYes() {
        return unitYes;
    }

    public MainUnit getUnitNo() {
        return unitNo;
    }

    public Savable<String> getTempSave() {
        return tempSave;
    }

    public PairInsert getPairInsert() {
        return pairInsert;
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

        public Builder tempSave(Savable<String> savable) {
            AnswerValidity.this.tempSave = savable;
            return this;
        }

        public Builder testInsert(PairInsert pairInsert) {
            AnswerValidity.this.pairInsert = pairInsert;
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
                Objects.equals(pairInsert, that.pairInsert);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), unitYes, unitNo, unitNull, tempSave, pairInsert);
    }
}

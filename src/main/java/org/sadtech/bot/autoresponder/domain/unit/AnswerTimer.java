package org.sadtech.bot.autoresponder.domain.unit;

import java.util.Objects;

public class AnswerTimer extends MainUnit {

    private MainUnit unitAnswer;
    private Integer timeDelaySec;
    private Integer personId;

    private AnswerTimer() {
        activeStatus = UnitActiveStatus.AFTER;
        typeUnit = TypeUnit.TIMER;
    }

    public MainUnit getUnitAnswer() {
        return unitAnswer;
    }

    public Integer getTimeDelaySec() {
        return timeDelaySec;
    }

    public Integer getPersonId() {
        return personId;
    }

    public static Builder builder() {
        return new AnswerTimer().new Builder();
    }

    public class Builder {

        private Builder() {

        }

        public Builder unitAnswer(MainUnit unitAnswer) {
            AnswerTimer.this.unitAnswer = unitAnswer;
            return this;
        }

        public Builder timeDelaySec(Integer sec) {
            AnswerTimer.this.timeDelaySec = sec;
            return this;
        }

        public Builder personId(Integer personId) {
            AnswerTimer.this.personId = personId;
            return this;
        }

        public AnswerTimer build() {
            return AnswerTimer.this;
        }

    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        AnswerTimer that = (AnswerTimer) o;
        return Objects.equals(unitAnswer, that.unitAnswer) &&
                Objects.equals(timeDelaySec, that.timeDelaySec) &&
                Objects.equals(personId, that.personId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), unitAnswer, timeDelaySec, personId);
    }
}

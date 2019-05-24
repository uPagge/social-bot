package org.sadtech.bot.autoresponder.domain.unit;

import org.sadtech.bot.autoresponder.domain.usercode.CheckData;

import java.util.Objects;

public class AnswerTimer extends MainUnit {

    private MainUnit unitAnswer;
    private Integer timeDelaySec;
    private Integer timeDeathSec;
    private Integer personId;
    private CheckData checkLoop;

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

    public CheckData getCheckLoop() {
        return checkLoop;
    }

    public Integer getTimeDeathSec() {
        return timeDeathSec;
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

        public Builder checkLoop(CheckData checkLoop) {
            AnswerTimer.this.checkLoop = checkLoop;
            return this;
        }

        public Builder timeDeathSec(Integer timeDeathSec) {
            AnswerTimer.this.timeDeathSec = timeDeathSec;
            return this;
        }

        public AnswerTimer build() {
            return AnswerTimer.this;
        }

    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AnswerTimer)) return false;
        if (!super.equals(o)) return false;
        AnswerTimer that = (AnswerTimer) o;
        return Objects.equals(unitAnswer, that.unitAnswer) &&
                Objects.equals(timeDelaySec, that.timeDelaySec) &&
                Objects.equals(personId, that.personId) &&
                Objects.equals(checkLoop, that.checkLoop);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), unitAnswer, timeDelaySec, personId, checkLoop);
    }
}

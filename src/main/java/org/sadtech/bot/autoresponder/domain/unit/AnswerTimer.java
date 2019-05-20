package org.sadtech.bot.autoresponder.domain.unit;

import java.util.Objects;

public class AnswerTimer extends MainUnit {

    private final MainUnit unitAnswer;
    private Long timeDelaySec;
    private Integer personId;

    public AnswerTimer(MainUnit unitAnswer) {
        this.unitAnswer = unitAnswer;
        activeStatus = UnitActiveStatus.AFTER;
        typeUnit = TypeUnit.TIMER;
    }

    public MainUnit getUnitAnswer() {
        return unitAnswer;
    }

    public Long getTimeDelaySec() {
        return timeDelaySec;
    }

    public void setTimeDelaySec(Long timeDelaySec) {
        this.timeDelaySec = timeDelaySec;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
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

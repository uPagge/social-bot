package org.sadtech.bot.autoresponder.domain.unit;

import java.util.Objects;

public class AnswerTimer extends MainUnit {

    private final MainUnit unitAnswer;
    private Long timeDelaySec;
    private Integer idUser;

    public AnswerTimer(MainUnit unitAnswer) {
        this.unitAnswer = unitAnswer;
        unitActiveStatus = UnitActiveStatus.AFTER;
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

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        AnswerTimer that = (AnswerTimer) o;
        return Objects.equals(unitAnswer, that.unitAnswer) &&
                Objects.equals(timeDelaySec, that.timeDelaySec) &&
                Objects.equals(idUser, that.idUser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), unitAnswer, timeDelaySec, idUser);
    }
}

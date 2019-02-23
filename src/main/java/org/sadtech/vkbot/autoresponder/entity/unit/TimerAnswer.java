package org.sadtech.vkbot.autoresponder.entity.unit;

public class TimerAnswer extends MainUnit {

    private MainUnit unitAnswer;
    private Long timeDelaySec;
    private Integer idUser;

    public TimerAnswer() {
        unitActiveStatus = UnitActiveStatus.AFTER;
        typeUnit = TypeUnit.TIMER;
    }

    public MainUnit getUnitAnswer() {
        return unitAnswer;
    }

    public void setUnitAnswer(MainUnit unitAnswer) {
        this.unitAnswer = unitAnswer;
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
}
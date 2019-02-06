package org.sadtech.vkbot.autoresponder.entity;

public class TimerAnswer extends MainUnit {

    private MainUnit unitAnswer;
    private Long timeDelaySec;
    private Integer idUser;

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

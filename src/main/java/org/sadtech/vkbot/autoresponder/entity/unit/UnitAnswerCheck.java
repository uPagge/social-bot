package org.sadtech.vkbot.autoresponder.entity.unit;

import org.sadtech.vkbot.autoresponder.entity.usercode.CheckData;

public class UnitAnswerCheck extends MainUnit {

    private MainUnit unitTrue;
    private MainUnit unitFalse;
    private CheckData check;
    private Integer userId;

    public UnitAnswerCheck() {

    }

    public MainUnit getUnitTrue() {
        return unitTrue;
    }

    public void setUnitTrue(MainUnit unitTrue) {
        this.unitTrue = unitTrue;
    }

    public MainUnit getUnitFalse() {
        return unitFalse;
    }

    public void setUnitFalse(MainUnit unitFalse) {
        this.unitFalse = unitFalse;
    }

    public CheckData getCheck() {
        return check;
    }

    public void setCheck(CheckData check) {
        this.check = check;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}

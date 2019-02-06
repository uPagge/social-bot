package org.sadtech.vkbot.autoresponder.entity;

import org.sadtech.vkbot.autoresponder.entity.usercode.CheckData;
import org.sadtech.vkbot.autoresponder.entity.usercode.PushData;

public class UnitAnswerCheck extends MainUnit {

    private MainUnit unitTrue;
    private MainUnit unitFalse;
    private PushData pushData;
    protected String userData;
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

    public PushData getPushData() {
        return pushData;
    }

    public void setPushData(PushData pushData) {
        this.pushData = pushData;
    }

    public String getUserData() {
        return userData;
    }

    public void setUserData(String userData) {
        this.userData = userData;
    }
}

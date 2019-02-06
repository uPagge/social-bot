package org.sadtech.vkbot.autoresponder.entity;

import org.sadtech.autoresponder.entity.Unit;

public abstract class MainUnit extends Unit {

    private Boolean hiddenTrigger = false;

    public Boolean getHiddenTrigger() {
        return hiddenTrigger;
    }

    public void setHiddenTrigger(Boolean hiddenTrigger) {
        this.hiddenTrigger = hiddenTrigger;
    }
}

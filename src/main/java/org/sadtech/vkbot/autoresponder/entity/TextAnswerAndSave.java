package org.sadtech.vkbot.autoresponder.entity;

import org.sadtech.vkbot.autoresponder.saver.Saver;

public class TextAnswerAndSave extends TextAnswer {

    private TextAnswerAndSave prevUnit;
    private Saver saver;
    private String key;

    public TextAnswerAndSave() {

    }


    public TextAnswerAndSave getPrevUnit() {
        return prevUnit;
    }

    public void setPrevUnit(TextAnswerAndSave prevUnit) {
        this.prevUnit = prevUnit;
    }

    public Saver getSaver() {
        return saver;
    }

    public void setSaver(Saver saver) {
        this.saver = saver;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}

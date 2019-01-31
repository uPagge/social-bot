package org.sadtech.vkbot.autoresponder.entity;

import org.sadtech.vkbot.autoresponder.saver.Saver;

import java.util.ArrayList;
import java.util.List;

public class TextAnswerAndSave extends TextAnswer {

    private List<TextAnswerAndSave> prevUnits = new ArrayList<>();
    private Saver saver;
    private String key;

    public TextAnswerAndSave() {
        super();
    }

    public void setPrevUnit(TextAnswerAndSave answerAndSave) {
        this.prevUnits.add(answerAndSave);
    }

    public List<TextAnswerAndSave> getPrevUnits() {
        return prevUnits;
    }

    public void setPrevUnits(List<TextAnswerAndSave> prevUnits) {
        this.prevUnits = prevUnits;
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

package org.sadtech.vkbot.autoresponder.entity;

import org.sadtech.vkbot.autoresponder.saver.Savable;
import org.sadtech.vkbot.autoresponder.saver.SaveStatus;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TextAnswerAndSave extends TextAnswer {

    private List<TextAnswerAndSave> prevUnits = new ArrayList<>();
    private Savable savable;
    private String key;
    private Set<SaveStatus> saveStatuses = new HashSet<>();

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

    public Savable getSavable() {
        return savable;
    }

    public void setSavable(Savable savable) {
        this.savable = savable;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Set<SaveStatus> getSaveStatuses() {
        return saveStatuses;
    }

    public void setSaveStatuses(Set<SaveStatus> saveStatuses) {
        this.saveStatuses = saveStatuses;
    }

    public void setSaveStatus(SaveStatus saveStatus) {
        this.saveStatuses.add(saveStatus);
    }

}

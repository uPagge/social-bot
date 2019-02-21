package org.sadtech.vkbot.autoresponder.entity.unit;

import org.sadtech.autoresponder.entity.Unit;
import org.sadtech.vkbot.autoresponder.saver.Savable;
import org.sadtech.vkbot.autoresponder.saver.SaveStatus;

import java.util.HashSet;
import java.util.Set;

public class AnswerSave extends MainUnit {

    private Savable savable;
    private String key;
    private Set<SaveStatus> saveStatuses = new HashSet<>();

    public AnswerSave() {

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

    @Override
    public void setNextUnit(Unit unit) {
        super.setNextUnit(unit);
        ((MainUnit) unit).setUnitActiveStatus(UnitActiveStatus.AFTER);
    }
}

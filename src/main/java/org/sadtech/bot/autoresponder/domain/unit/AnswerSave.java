package org.sadtech.bot.autoresponder.domain.unit;

import org.sadtech.autoresponder.entity.Unit;
import org.sadtech.bot.autoresponder.domain.usercode.SaveData;
import org.sadtech.bot.autoresponder.saver.Savable;
import org.sadtech.bot.autoresponder.saver.SaveStatus;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class AnswerSave extends MainUnit {

    private final Savable savable;
    private String key;
    private Set<SaveStatus> saveStatuses = new HashSet<>();
    private SaveData saveData;
    private final Boolean hidden;


    public AnswerSave(Savable savable, Boolean hidden) {
        this.savable = savable;
        typeUnit = TypeUnit.SAVE;
        this.hidden = hidden;
        if (hidden) {
            activeStatus = UnitActiveStatus.AFTER;
        } else {
            activeStatus = UnitActiveStatus.DEFAULT;
        }
    }

    public AnswerSave(Savable savable) {
        this.savable = savable;
        typeUnit = TypeUnit.SAVE;
        hidden = false;
    }

    public Savable getSavable() {
        return savable;
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

    public void setSaveStatus(SaveStatus... saveStatus) {
        this.saveStatuses.addAll(Arrays.asList(saveStatus));
    }

    public SaveData getSaveData() {
        return saveData;
    }

    public void setSaveData(SaveData saveData) {
        this.saveData = saveData;
    }

    public Boolean getHidden() {
        return hidden;
    }

    @Override
    public void setNextUnit(Unit... units) {
        super.setNextUnit(units);
        for (Unit unit : units) {
            ((MainUnit) unit).setActiveStatus(UnitActiveStatus.AFTER);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AnswerSave)) return false;
        if (!super.equals(o)) return false;
        AnswerSave that = (AnswerSave) o;
        return Objects.equals(savable, that.savable) &&
                Objects.equals(key, that.key) &&
                Objects.equals(saveStatuses, that.saveStatuses) &&
                Objects.equals(saveData, that.saveData) &&
                Objects.equals(hidden, that.hidden);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), savable, key, saveStatuses, saveData, hidden);
    }
}

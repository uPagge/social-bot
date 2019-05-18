package org.sadtech.bot.autoresponder.domain.unit;

import org.sadtech.autoresponder.entity.Unit;
import org.sadtech.bot.autoresponder.saver.Savable;
import org.sadtech.bot.autoresponder.saver.SaveStatus;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class AnswerSave extends MainUnit {

    private final Savable savable;
    private final String key;
    private Set<SaveStatus> saveStatuses = new HashSet<>();

    public AnswerSave(String key, Savable savable) {
        this.savable = savable;
        this.key = key;
        typeUnit = TypeUnit.SAVE;
    }

    public Savable getSavable() {
        return savable;
    }


    public String getKey() {
        return key;
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
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        AnswerSave that = (AnswerSave) o;
        return Objects.equals(savable, that.savable) &&
                Objects.equals(key, that.key) &&
                Objects.equals(saveStatuses, that.saveStatuses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), savable, key, saveStatuses);
    }
}

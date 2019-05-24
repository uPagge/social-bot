package org.sadtech.bot.autoresponder.domain.unit;

import org.sadtech.autoresponder.entity.Unit;
import org.sadtech.bot.autoresponder.domain.usercode.SaveData;
import org.sadtech.bot.autoresponder.save.Savable;
import org.sadtech.bot.autoresponder.save.SaveStatus;

import java.util.*;

public class AnswerSave extends MainUnit {

    private Savable savable;
    private String key;
    private Set<SaveStatus> saveStatuses = new HashSet<>();
    private SaveData saveData;
    private Boolean hidden = false;

    private AnswerSave() {
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

    public SaveData getSaveData() {
        return saveData;
    }

    public Boolean getHidden() {
        return hidden;
    }

    public static Builder builder() {
        return new AnswerSave().new Builder();
    }

    public class Builder {

        private Builder() {

        }

        public Builder savable(Savable savable) {
            AnswerSave.this.savable = savable;
            return this;
        }

        public Builder key(String key) {
            AnswerSave.this.key = key;
            return this;
        }

        public Builder saveStatus(SaveStatus... saveStatuses) {
            AnswerSave.this.saveStatuses.addAll(Arrays.asList(saveStatuses));
            return this;
        }

        public Builder nextUnit(MainUnit... mainUnits) {
            AnswerSave.this.setNextUnit(mainUnits);
            return this;
        }

        public Builder nextUnits(Set<Unit> mainUnits) {
            AnswerSave.this.setNextUnits(mainUnits);
            return this;
        }

        public Builder saveData(SaveData saveData) {
            AnswerSave.this.saveData = saveData;
            return this;
        }

        public Builder hidden(Boolean hidden) {
            AnswerSave.this.hidden = hidden;
            activeStatus = (hidden) ? UnitActiveStatus.AFTER : UnitActiveStatus.DEFAULT;
            return this;
        }

        public AnswerSave build() {
            return AnswerSave.this;
        }

    }

    @Override
    public void setNextUnit(Unit... units) {
        super.setNextUnit(units);
        maintenanceNextUnit(Arrays.asList(units));
    }

    @Override
    public void setNextUnits(Set<Unit> nextUnits) {
        super.setNextUnits(nextUnits);
        maintenanceNextUnit(nextUnits);
    }

    private void maintenanceNextUnit(Collection<Unit> units) {
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

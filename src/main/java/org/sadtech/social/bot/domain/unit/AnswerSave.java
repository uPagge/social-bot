package org.sadtech.social.bot.domain.unit;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.sadtech.autoresponder.entity.Unit;
import org.sadtech.social.bot.service.save.Savable;
import org.sadtech.social.bot.service.save.SaveType;
import org.sadtech.social.bot.service.usercode.SaveData;
import org.sadtech.social.core.utils.Description;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Обработчик для сохранения ответов пользователя. Так же допускается скрытое сохранение.
 *
 * @author upagge [08/07/2019]
 */
@Getter
@EqualsAndHashCode(callSuper = true)
public class AnswerSave extends MainUnit {

    @Description("Объект отвечающий за сохранение - репозиторий")
    private Savable savable;

    @Description("Наименование поля сохранения")
    private String key;

    @Description("Тип сохранения")
    private Set<SaveType> saveTypes = new HashSet<>();

    @Description("Данные для скрытого сохранения")
    private SaveData saveData;

    @Description("Скрытое сохранение")
    private Boolean hidden = false;

    public AnswerSave() {
        typeUnit = TypeUnit.SAVE;
    }

    public Savable getSavable() {
        return savable;
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

        public Builder saveStatus(SaveType... saveTypes) {
            AnswerSave.this.saveTypes.addAll(Arrays.asList(saveTypes));
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
            activeType = (hidden) ? UnitActiveType.AFTER : UnitActiveType.DEFAULT;
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
            ((MainUnit) unit).setActiveType(UnitActiveType.AFTER);
        }
    }

}

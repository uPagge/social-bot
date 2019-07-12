package org.sadtech.social.bot.domain.unit;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Singular;
import org.sadtech.autoresponder.entity.Unit;
import org.sadtech.social.bot.service.save.Savable;
import org.sadtech.social.bot.service.save.SaveType;
import org.sadtech.social.bot.service.usercode.SaveData;
import org.sadtech.social.core.utils.Description;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * Обработчик для сохранения ответов пользователя. Так же допускается скрытое сохранение.
 *
 * @author upagge [08/07/2019]
 */
@Getter
@EqualsAndHashCode(callSuper = true)
public class AnswerSave extends MainUnit {

    @Description("Объект отвечающий за сохранение - репозиторий")
    private final Savable savable;

    @Description("Наименование поля сохранения")
    private final String key;

    @Description("Тип сохранения")
    private final Set<SaveType> saveTypes;

    @Description("Данные для скрытого сохранения")
    private final SaveData saveData;

    @Description("Скрытое сохранение")
    private final boolean hidden;

    @Builder
    private AnswerSave(@Singular Set<String> keyWords,
                       Pattern pattern,
                       Integer matchThreshold,
                       Integer priority,
                       @Singular Set<Unit> nextUnits,
                       Savable savable,
                       String key,
                       @Singular Set<SaveType> saveTypes,
                       SaveData saveData,
                       boolean hidden) {
        super(keyWords, pattern, matchThreshold, priority, nextUnits, (hidden) ? UnitActiveType.AFTER : UnitActiveType.DEFAULT, TypeUnit.SAVE);
        maintenanceNextUnit(nextUnits);
        this.savable = savable;
        this.key = key;
        this.saveTypes = saveTypes;
        this.saveData = saveData;
        this.hidden = Optional.of(hidden).orElse(false);
    }

    private void maintenanceNextUnit(Collection<Unit> units) {
        for (Unit unit : units) {
            ((MainUnit) unit).setActiveType(UnitActiveType.AFTER);
        }
    }

}

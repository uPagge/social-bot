package org.sadtech.social.bot.domain.unit;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Singular;
import org.sadtech.autoresponder.entity.Unit;
import org.sadtech.social.bot.service.save.Preservable;
import org.sadtech.social.bot.service.save.data.PreservableData;
import org.sadtech.social.bot.service.save.push.Pusher;
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
public class AnswerSave<D> extends MainUnit {

    @Description("Объект отвечающий за сохранение - репозиторий")
    private final Preservable<D> preservable;

    @Description("Отправка результатов")
    private final Pusher<D> pusher;

    @Description("Данные для скрытого сохранения")
    private final PreservableData<D, ?> preservableData;

    @Description("Скрытое сохранение")
    private final boolean hidden;


    @Builder
    private AnswerSave(@Singular Set<String> keyWords,
                       String phrase,
                       Pattern pattern,
                       Integer matchThreshold,
                       Integer priority,
                       @Singular Set<Unit> nextUnits,
                       Preservable<D> preservable,
                       Pusher<D> pusher,
                       PreservableData<D, ?> preservableData,
                       boolean hidden) {
        super(keyWords, phrase, pattern, matchThreshold, priority, nextUnits, (hidden) ? UnitActiveType.AFTER : UnitActiveType.DEFAULT, TypeUnit.SAVE);
        this.pusher = pusher;
        maintenanceNextUnit(nextUnits);
        this.preservable = preservable;
        this.preservableData = preservableData;
        this.hidden = Optional.of(hidden).orElse(false);
    }

    private void maintenanceNextUnit(Collection<Unit> units) {
        for (Unit unit : units) {
            ((MainUnit) unit).setActiveType(UnitActiveType.AFTER);
        }
    }

}

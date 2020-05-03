package org.sadtech.social.bot.domain.unit;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.sadtech.autoresponder.entity.Unit;
import org.sadtech.social.core.utils.Description;

import java.util.Optional;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * Главный обработчик {@link Unit}, от него наследуются все остальные Unit-ы.
 *
 * @author upagge [08/07/2019]
 */
@ToString
@EqualsAndHashCode(callSuper = true)
public abstract class MainUnit extends Unit<MainUnit> {

    @Getter
    @Description("Тип Unit-а")
    protected final String type;
    @Getter
    @Setter
    @Description("Режим срабатывания Unit-а")
    protected UnitActiveType activeType;

    protected MainUnit(Set<String> keyWords,
                       String phrase,
                       Pattern pattern,
                       Integer matchThreshold,
                       Integer priority,
                       Set<MainUnit> nextUnits,
                       UnitActiveType activeType,
                       String type) {
        super(keyWords, phrase, pattern, matchThreshold, priority, nextUnits);
        this.activeType = Optional.ofNullable(activeType).orElse(UnitActiveType.DEFAULT);
        this.type = type;
    }

}

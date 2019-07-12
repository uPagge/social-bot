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
@EqualsAndHashCode(callSuper = true)
@ToString
public abstract class MainUnit extends Unit {

    @Getter
    @Setter
    @Description("Тип срабатывания Unit-а")
    protected UnitActiveType activeType;

    @Getter
    @Description("Тип Unit-а")
    protected final TypeUnit typeUnit;

    protected MainUnit(Set<String> keyWords,
                       Pattern pattern,
                       Integer matchThreshold,
                       Integer priority,
                       Set<Unit> nextUnits,
                       UnitActiveType activeType,
                       TypeUnit typeUnit) {
        super(keyWords, pattern, matchThreshold, priority, nextUnits);
        this.activeType = Optional.ofNullable(activeType).orElse(UnitActiveType.DEFAULT);
        this.typeUnit = typeUnit;
    }
}

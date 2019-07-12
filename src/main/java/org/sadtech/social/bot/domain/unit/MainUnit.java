package org.sadtech.social.bot.domain.unit;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.sadtech.autoresponder.entity.Unit;
import org.sadtech.social.core.utils.Description;

/**
 * Главный обработчик {@link Unit}, от него наследуются все остальные Unit-ы.
 *
 * @author upagge [08/07/2019]
 */
@EqualsAndHashCode(callSuper = true)
public abstract class MainUnit extends Unit {

    @Getter
    @Setter
    @Description("Тип срабатывания Unit-а")
    protected UnitActiveType activeType = UnitActiveType.DEFAULT;

    @Getter
    @Description("Тип Unit-а")
    protected TypeUnit typeUnit;

}

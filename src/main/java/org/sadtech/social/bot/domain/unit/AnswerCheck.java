package org.sadtech.social.bot.domain.unit;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Singular;
import org.sadtech.autoresponder.entity.Unit;
import org.sadtech.social.bot.service.usercode.CheckData;
import org.sadtech.social.core.utils.Description;

import java.util.Set;
import java.util.regex.Pattern;

/**
 * Обработчик запроса, который реализует конструкцию IF в сценарии.
 *
 * @author upagge [08/07/2019]
 */
@Getter
@EqualsAndHashCode(callSuper = true)
public class AnswerCheck extends MainUnit {

    @Description("Unit для true")
    private MainUnit unitTrue;

    @Description("Unit для false")
    private MainUnit unitFalse;

    @Description("Условие проверки")
    private CheckData check;

    @Builder
    public AnswerCheck(@Singular Set<String> keyWords,
                       Pattern pattern,
                       Integer matchThreshold,
                       Integer priority,
                       @Singular Set<Unit> nextUnits,
                       UnitActiveType activeType,
                       MainUnit unitTrue,
                       MainUnit unitFalse,
                       CheckData check) {
        super(keyWords, pattern, matchThreshold, priority, nextUnits, activeType, TypeUnit.CHECK);
        this.unitTrue = unitTrue;
        this.unitFalse = unitFalse;
        this.check = check;
    }

}

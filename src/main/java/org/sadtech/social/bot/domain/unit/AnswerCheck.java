package org.sadtech.social.bot.domain.unit;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Singular;
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
    private final MainUnit unitTrue;

    @Description("Unit для false")
    private final MainUnit unitFalse;

    @Description("Условие проверки")
    private final CheckData check;

    @Builder
    protected AnswerCheck(@Singular Set<String> keyWords,
                          String phrase,
                          Pattern pattern,
                          Integer matchThreshold,
                          Integer priority,
                          @Singular Set<MainUnit> nextUnits,
                          UnitActiveType activeType,
                          MainUnit unitTrue,
                          MainUnit unitFalse,
                          CheckData check) {
        super(keyWords, phrase, pattern, matchThreshold, priority, nextUnits, activeType, TypeUnit.CHECK);
        this.unitTrue = unitTrue;
        this.unitFalse = unitFalse;
        this.check = check;
    }
}

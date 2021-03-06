package org.sadtech.social.bot.domain.unit;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Singular;
import org.sadtech.social.bot.domain.AccountAutoCheck;
import org.sadtech.social.bot.utils.TypeUnit;
import org.sadtech.social.core.utils.Description;

import java.util.Set;
import java.util.regex.Pattern;

/**
 * Юнит для обработки платежей.
 *
 * @author upagge [08/07/2019]
 */
@Getter
@EqualsAndHashCode(callSuper = true)
public class AnswerAccount extends MainUnit {

    @Description("Сумма к оплате")
    private final Integer totalSum;

    @Description("Время жизни счета")
    private final Integer timeHours;

    @Description("Настройки для автоматической проверки оплаты")
    private final AccountAutoCheck autoCheck;

    @Builder
    private AnswerAccount(
            @Singular Set<String> keyWords,
            String phrase,
            Pattern pattern,
            Integer matchThreshold,
            Integer priority,
            @Singular Set<MainUnit> nextUnits,
            UnitActiveType activeType,
            Integer totalSum,
            Integer timeHours,
            AccountAutoCheck autoCheck
    ) {
        super(
                keyWords,
                phrase,
                pattern,
                matchThreshold,
                priority,
                nextUnits,
                (activeType == null) ? UnitActiveType.AFTER : activeType,
                TypeUnit.ACCOUNT
        );
        this.totalSum = totalSum;
        this.timeHours = timeHours;
        this.autoCheck = autoCheck;
    }

}

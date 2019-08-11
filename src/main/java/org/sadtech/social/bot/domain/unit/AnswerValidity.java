package org.sadtech.social.bot.domain.unit;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Singular;
import lombok.ToString;
import org.sadtech.social.bot.service.save.Preservable;
import org.sadtech.social.bot.service.usercode.PairInsert;
import org.sadtech.social.core.utils.Description;

import java.util.Set;
import java.util.regex.Pattern;

/**
 * Обработка данных со страницы пользователя.
 *
 * @author upagge [11/07/2019]
 */
@EqualsAndHashCode(callSuper = true)
@ToString
@Getter
public class AnswerValidity extends MainUnit {

    @Description("Unit обрабатывается, если пользователь подтверждает данные")
    private final MainUnit unitYes;

    @Description("Unit обрабатывается, если пользователь откланяет данные")
    private final MainUnit unitNo;

    @Description("Unit обрабатывается, если данные не найдены")
    private final MainUnit unitNull;

    @Description("")
    private final Preservable<String> tempSave;

    @Description("")
    private final PairInsert pairInsert;

    @Builder(toBuilder = true)
    private AnswerValidity(@Singular Set<String> keyWords,
                           String phrase,
                           Pattern pattern,
                           Integer matchThreshold,
                           Integer priority,
                           @Singular Set<MainUnit> nextUnits,
                           UnitActiveType activeType,
                           MainUnit unitYes,
                           MainUnit unitNo,
                           MainUnit unitNull,
                           Preservable<String> tempSave,
                           PairInsert pairInsert) {
        super(keyWords, phrase, pattern, matchThreshold, priority, nextUnits, (activeType == null) ? UnitActiveType.AFTER : activeType, TypeUnit.VALIDITY);
        this.unitYes = unitYes;
        this.unitNo = unitNo;
        this.unitNull = unitNull;
        this.tempSave = tempSave;
        this.pairInsert = pairInsert;
    }

}

package org.sadtech.social.bot.domain.unit;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Singular;
import lombok.ToString;
import org.sadtech.autoresponder.entity.Unit;
import org.sadtech.social.bot.service.action.AnswerValidityAction;
import org.sadtech.social.bot.service.save.Savable;
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
    private final Savable<String> tempSave;

    @Description("")
    private final PairInsert pairInsert;

    @Builder(toBuilder = true)
    private AnswerValidity(@Singular Set<String> keyWords,
                           Pattern pattern,
                           Integer matchThreshold,
                           Integer priority,
                           @Singular Set<Unit> nextUnits,
                           UnitActiveType activeType,
                           MainUnit unitYes,
                           MainUnit unitNo,
                           MainUnit unitNull,
                           Savable<String> tempSave,
                           PairInsert pairInsert) {
        super(keyWords, pattern, matchThreshold, priority, nextUnits, (activeType == null) ? UnitActiveType.AFTER : activeType, TypeUnit.VALIDITY);
        this.unitYes = unitYes;
        this.unitNo = unitNo;
        this.unitNull = unitNull;
        this.tempSave = tempSave;
        this.pairInsert = pairInsert;
    }

    private void init() {
        if (unitYes != null) {
            this.setKeyWords(AnswerValidityAction.WORDS_YES);
        }
        if (unitNo != null) {
            this.setKeyWords(AnswerValidityAction.WORDS_NO);
        }
    }


}

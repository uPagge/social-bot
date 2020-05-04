package org.sadtech.social.bot.domain.unit;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Singular;
import lombok.ToString;
import org.sadtech.social.bot.service.save.LocalPreservable;
import org.sadtech.social.bot.service.save.Preservable;
import org.sadtech.social.bot.service.usercode.ClarificationQuestion;
import org.sadtech.social.bot.utils.TypeUnit;
import org.sadtech.social.core.utils.Description;

import java.util.Set;
import java.util.regex.Pattern;

/**
 * Обработка данных со страницы пользователя.
 *
 * @author upagge [11/07/2019]
 */
@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
public class AnswerValidity extends MainUnit {

    @Description("Unit обрабатывается, если пользователь подтверждает данные")
    private final MainUnit unitYes;

    @Description("Unit обрабатывается, если пользователь откланяет данные")
    private final MainUnit unitNo;

    @Description("Unit обрабатывается, если данные не найдены")
    private final MainUnit unitNull;

    @Description("")
    private final Preservable<String> tempSave = new LocalPreservable<>();

    @Description("")
    private final ClarificationQuestion clarificationQuestion;

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
                           ClarificationQuestion clarificationQuestion) {
        super(keyWords, phrase, pattern, matchThreshold, priority, nextUnits, UnitActiveType.DEFAULT, TypeUnit.VALIDITY);
        this.unitYes = unitYes;
        this.unitNo = unitNo;
        this.unitNull = unitNull;
        this.clarificationQuestion = clarificationQuestion;
    }

}

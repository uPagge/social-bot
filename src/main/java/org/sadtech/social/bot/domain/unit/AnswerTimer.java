package org.sadtech.social.bot.domain.unit;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Singular;
import lombok.ToString;
import org.sadtech.autoresponder.entity.Unit;
import org.sadtech.social.bot.service.usercode.CheckData;
import org.sadtech.social.core.utils.Description;

import java.util.Set;
import java.util.regex.Pattern;

/**
 * Обработчик таймер, позволяющий отложить обработку других Unit-ов.
 *
 * @author upagge [08/07/2019]
 */
@Getter
@EqualsAndHashCode(callSuper = true)
@ToString
public class AnswerTimer extends MainUnit {

    @Description("Unit обработку которого необходимо отложить")
    private final MainUnit unitAnswer;

    @Description("Задержка обработки в секундах")
    private final Integer timeDelaySec;

    @Description("Время, через которое таймер будет удален в секундах")
    private final Integer timeDeathSec;

    @Description("Условие срабатывания отложенного Unit")
    private final CheckData checkLoop;

    @Builder
    private AnswerTimer(@Singular Set<String> keyWords,
                        String phrase,
                        Pattern pattern,
                        Integer matchThreshold,
                        Integer priority,
                        @Singular Set<Unit> nextUnits,
                        UnitActiveType activeType,
                        MainUnit unitAnswer,
                        Integer timeDelaySec,
                        Integer timeDeathSec,
                        CheckData checkLoop) {
        super(keyWords, phrase, pattern, matchThreshold, priority, nextUnits, (activeType == null) ? UnitActiveType.AFTER : activeType, TypeUnit.TIMER);
        this.unitAnswer = unitAnswer;
        this.timeDelaySec = timeDelaySec;
        this.timeDeathSec = timeDeathSec;
        this.checkLoop = checkLoop;
    }

}

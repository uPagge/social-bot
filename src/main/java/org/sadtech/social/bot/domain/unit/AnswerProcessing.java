package org.sadtech.social.bot.domain.unit;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Singular;
import org.sadtech.social.bot.service.usercode.ProcessingData;
import org.sadtech.social.core.domain.content.Message;
import org.sadtech.social.core.service.sender.Sent;
import org.sadtech.social.core.utils.Description;

import java.util.Set;
import java.util.regex.Pattern;

/**
 * Обработчик для кастомных реализаций.
 *
 * @author upagge [08/07/2019]
 */
@Getter
@EqualsAndHashCode(callSuper = true)
public class AnswerProcessing<M extends Message> extends MainUnit {

    @Description("Кастомная обработка")
    private final ProcessingData<M> processingData;

    @Description("Объект для сквозной отправки ответа")
    private final Sent sent;

    @Builder
    private AnswerProcessing(@Singular Set<String> keyWords,
                             String phrase,
                             Pattern pattern,
                             Integer matchThreshold,
                             Integer priority,
                             @Singular Set<MainUnit> nextUnits,
                             UnitActiveType activeType,
                             ProcessingData<M> processingData,
                             Sent sent) {
        super(keyWords, phrase, pattern, matchThreshold, priority, nextUnits, activeType, TypeUnit.PROCESSING);
        this.processingData = processingData;
        this.sent = sent;
    }

}

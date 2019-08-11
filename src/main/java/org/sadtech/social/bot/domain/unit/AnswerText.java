package org.sadtech.social.bot.domain.unit;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Singular;
import org.sadtech.social.bot.service.usercode.Insert;
import org.sadtech.social.core.domain.BoxAnswer;
import org.sadtech.social.core.service.sender.Sent;
import org.sadtech.social.core.utils.Description;

import java.util.Set;
import java.util.regex.Pattern;

/**
 * Обработчик, который отпарвляет обычный ответ пользователю.
 *
 * @author upagge [08/07/2019]
 */
@EqualsAndHashCode(callSuper = true)
@Getter
public class AnswerText extends MainUnit {

    @Description("Объект, который необходимо отправить пользователю")
    private final BoxAnswer boxAnswer;

    @Description("Информация, которую необходимо вставить вместо маркеров в строку ответа")
    private final Insert insert;

    @Description("Объект нестандартной отправки ответа")
    private final Sent sent;

    @Builder(toBuilder = true)
    private AnswerText(@Singular Set<String> keyWords,
                       String phrase,
                       Pattern pattern,
                       Integer matchThreshold,
                       Integer priority,
                       @Singular Set<MainUnit> nextUnits,
                       UnitActiveType activeType,
                       BoxAnswer boxAnswer,
                       Insert insert,
                       Sent sent) {
        super(keyWords, phrase, pattern, matchThreshold, priority, nextUnits, activeType, TypeUnit.TEXT);
        this.boxAnswer = boxAnswer;
        this.insert = insert;
        this.sent = sent;
    }
}

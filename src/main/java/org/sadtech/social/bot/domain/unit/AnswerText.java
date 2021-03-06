package org.sadtech.social.bot.domain.unit;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Singular;
import org.sadtech.social.bot.service.usercode.Insert;
import org.sadtech.social.bot.utils.TypeUnit;
import org.sadtech.social.core.domain.BoxAnswer;
import org.sadtech.social.core.service.sender.Sending;
import org.sadtech.social.core.utils.Description;

import java.util.Set;
import java.util.regex.Pattern;

/**
 * Используется для отправки ответа пользователю.
 *
 * @author upagge [08/07/2019]
 */
@Getter
@EqualsAndHashCode(callSuper = true)
public class AnswerText extends MainUnit {

    @Description("Объект, который необходимо отправить пользователю")
    private final BoxAnswer boxAnswer;

    @Description("Информация, которую необходимо вставить вместо маркеров в строку ответа")
    private final Insert insert;

    @Description("Объект нестандартной отправки ответа")
    private final Sending sending;

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
                       Sending sending) {
        super(keyWords, phrase, pattern, matchThreshold, priority, nextUnits, activeType, TypeUnit.TEXT);
        this.boxAnswer = boxAnswer;
        this.insert = insert;
        this.sending = sending;
    }

    public static AnswerText of(String message) {
        return AnswerText.builder().boxAnswer(BoxAnswer.of(message)).build();
    }

}

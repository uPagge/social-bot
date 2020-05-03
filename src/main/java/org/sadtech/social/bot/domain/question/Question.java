package org.sadtech.social.bot.domain.question;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.Singular;
import org.sadtech.social.core.domain.BoxAnswer;
import org.sadtech.social.core.utils.Description;

import java.util.List;

/**
 * Используется для конфигурации генерации цепочки Unit-ов в виде тестов для прохождения пользователем.
 *
 * @author upagge [14/07/2019]
 */
@Getter
@Setter
@Builder
public class Question {

    @Description("Вопрос")
    private BoxAnswer boxAnswer;

    @Singular
    @Description("Список предполагаемых ответов")
    private List<QuestionAnswer> questionAnswers;

}

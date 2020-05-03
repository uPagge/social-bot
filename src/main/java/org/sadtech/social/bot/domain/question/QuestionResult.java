package org.sadtech.social.bot.domain.question;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.sadtech.social.core.utils.Description;

/**
 * Используется для сохранения результатов ответов на вопросы.
 *
 * @author upagge [14/07/2019]
 */
@Getter
@Setter
@AllArgsConstructor
public class QuestionResult {

    @Description("Вопрос")
    private String question;

    @Description("Ответ")
    private String answer;

    @Description("Количество баллов за ответ")
    private Integer points;

}

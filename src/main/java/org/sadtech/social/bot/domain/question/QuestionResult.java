package org.sadtech.social.bot.domain.question;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.sadtech.social.core.utils.Description;

/**
 * Обхект для сохранения результатов ответов на вопросы.
 *
 * @author upagge [14/07/2019]
 */
@Data
@AllArgsConstructor
public class QuestionResult {

    @Description("Вопрос")
    private String question;

    @Description("Ответ")
    private String answer;

    @Description("Количество баллов за ответ")
    private Integer points;

}

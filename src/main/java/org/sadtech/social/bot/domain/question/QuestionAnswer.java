package org.sadtech.social.bot.domain.question;

import lombok.Getter;
import lombok.Setter;
import org.sadtech.social.core.utils.Description;

/**
 * ИСпользуется для конфигурации генерации цепочки Unit-ов в виде тестов для прохождения пользователем.
 * Отвечает за варианты ответов.
 *
 * @author upagge [14/07/2019]
 */
@Getter
@Setter
public class QuestionAnswer {

    @Description("Текстовый ответ")
    private String text;

    @Description("Количество балов за ответ")
    private int points;

    public QuestionAnswer(String text, Integer points) {
        this.text = text;
        this.points = points;
    }

    public QuestionAnswer(String text) {
        this.text = text;
    }

}

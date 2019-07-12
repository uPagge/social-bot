package org.sadtech.social.bot.domain.unit;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.sadtech.social.bot.service.usercode.Insert;
import org.sadtech.social.core.domain.BoxAnswer;
import org.sadtech.social.core.service.sender.Sent;
import org.sadtech.social.core.utils.Description;

/**
 * Обработчик, который отпарвляет обычный ответ пользователю.
 *
 * @author upagge [08/07/2019]
 */
@EqualsAndHashCode
@Getter
public class AnswerText extends MainUnit {

    @Description("Объект, который необходимо отправить пользователю")
    private BoxAnswer boxAnswer;

    @Description("Информация, которую необходимо вставить вместо маркеров в строку ответа")
    private Insert insert;

    @Description("Объект нестандартной отправки ответа")
    private Sent sent;

    public AnswerText() {
        typeUnit = TypeUnit.TEXT;
    }


    public static Builder builder() {
        return new AnswerText().new Builder();
    }

    public class Builder {
        private Builder() {

        }

        public Builder boxAnswer(BoxAnswer boxAnswer) {
            AnswerText.this.boxAnswer = boxAnswer;
            return this;
        }

        public Builder sent(Sent sent) {
            AnswerText.this.sent = sent;
            return this;
        }

        public Builder insert(Insert insert) {
            AnswerText.this.insert = insert;
            return this;
        }

        public AnswerText build() {
            return AnswerText.this;
        }
    }

}

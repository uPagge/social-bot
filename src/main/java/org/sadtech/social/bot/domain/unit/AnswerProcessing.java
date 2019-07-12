package org.sadtech.social.bot.domain.unit;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.sadtech.social.bot.service.usercode.ProcessingData;
import org.sadtech.social.core.service.sender.Sent;
import org.sadtech.social.core.utils.Description;

/**
 * Обработчик для кастомных реализаций.
 *
 * @author upagge [08/07/2019]
 */
@Getter
@EqualsAndHashCode(callSuper = true)
public class AnswerProcessing extends MainUnit {

    @Description("Кастомная обработка")
    private ProcessingData processingData;

    @Description("Объект для сквозной отправки ответа")
    private Sent sent;

    public AnswerProcessing() {
        typeUnit = TypeUnit.PROCESSING;
    }

    public static Builder builder() {
        return new AnswerProcessing().new Builder();
    }

    public class Builder {
        private Builder() {

        }

        public Builder processingData(ProcessingData processingData) {
            AnswerProcessing.this.processingData = processingData;
            return this;
        }

        public Builder sent(Sent sent) {
            AnswerProcessing.this.sent = sent;
            return this;
        }

        public AnswerProcessing build() {
            return AnswerProcessing.this;
        }

    }
}

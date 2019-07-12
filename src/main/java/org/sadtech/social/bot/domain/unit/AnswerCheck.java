package org.sadtech.social.bot.domain.unit;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.sadtech.social.bot.service.usercode.CheckData;
import org.sadtech.social.core.utils.Description;

/**
 * Обработчик запроса, который реализует конструкцию IF в сценарии.
 *
 * @author upagge [08/07/2019]
 */
@Getter
@EqualsAndHashCode(callSuper = true)
public class AnswerCheck extends MainUnit {

    @Description("Unit для true")
    private MainUnit unitTrue;

    @Description("Unit для false")
    private MainUnit unitFalse;

    @Description("Условие проверки")
    private CheckData check;

    public AnswerCheck() {
        typeUnit = TypeUnit.CHECK;
    }

    public static Builder builder() {
        return new AnswerCheck().new Builder();
    }

    public class Builder {
        private Builder() {

        }

        public Builder unitTrue(MainUnit unitTrue) {
            AnswerCheck.this.unitTrue = unitTrue;
            return this;
        }

        public Builder unitFalse(MainUnit unitFalse) {
            AnswerCheck.this.unitFalse = unitFalse;
            return this;
        }

        public Builder check(CheckData checkData) {
            AnswerCheck.this.check = checkData;
            return this;
        }

        public AnswerCheck build() {
            return AnswerCheck.this;
        }
    }

}

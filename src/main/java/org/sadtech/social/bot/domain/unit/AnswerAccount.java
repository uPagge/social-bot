package org.sadtech.social.bot.domain.unit;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.sadtech.social.bot.domain.AccountAutoCheck;
import org.sadtech.social.core.utils.Description;

/**
 * Данные для обработки счета.
 *
 * @author upagge [08/07/2019]
 */
@Getter
@EqualsAndHashCode(callSuper = true)
public class AnswerAccount extends MainUnit {

    @Description("Сумма к оплате")
    private Integer totalSum;

    @Description("Время жизни счета")
    private Integer timeHours;

    @Description("Настройки для автоматической проверки оплаты")
    private AccountAutoCheck autoCheck;

    public AnswerAccount() {
        activeType = UnitActiveType.AFTER;
        typeUnit = TypeUnit.ACCOUNT;
    }

    public static Builder builder() {
        return new AnswerAccount().new Builder();
    }

    public class Builder {
        private Builder() {

        }

        public Builder totalSum(Integer totalSum) {
            AnswerAccount.this.totalSum = totalSum;
            return this;
        }

        public Builder timeHours(Integer timeHours) {
            AnswerAccount.this.timeHours = timeHours;
            return this;
        }

        public Builder autoCheck(AccountAutoCheck autoCheck) {
            AnswerAccount.this.autoCheck = autoCheck;
            return this;
        }

        public AnswerAccount build() {
            return AnswerAccount.this;
        }

    }

}

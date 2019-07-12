package org.sadtech.social.bot.domain;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.sadtech.social.bot.domain.unit.MainUnit;
import org.sadtech.social.core.domain.money.Account;
import org.sadtech.social.core.utils.Description;

/**
 * Объект, для автоматической проверки оплаты счета {@link Account}.
 *
 * @author upagge [11/07/2019]
 */
@Getter
@EqualsAndHashCode
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AccountAutoCheck {

    @Description("Unut, который обрабатывается при успешной оплате")
    private MainUnit successfulPayment;

    @Description("Unit, который обрабатывается при не успешной оплате")
    private MainUnit failedPayment;

    @Description("Период проверки")
    private Integer periodSec;

    @Description("Время жизни счета")
    private Integer lifeTimeHours;

    public static Builder builder() {
        return new AccountAutoCheck().new Builder();
    }

    public class Builder {
        private Builder() {

        }

        public Builder successfulPayment(MainUnit successfulPayment) {
            AccountAutoCheck.this.successfulPayment = successfulPayment;
            return this;
        }

        public Builder failedPayment(MainUnit failedPayment) {
            AccountAutoCheck.this.failedPayment = failedPayment;
            return this;
        }

        public Builder periodSec(Integer periodSec) {
            AccountAutoCheck.this.periodSec = periodSec;
            return this;
        }

        public Builder lifeTimeHours(Integer lifeTimeHours) {
            AccountAutoCheck.this.lifeTimeHours = lifeTimeHours;
            return this;
        }

        public AccountAutoCheck build() {
            return AccountAutoCheck.this;
        }

    }

}

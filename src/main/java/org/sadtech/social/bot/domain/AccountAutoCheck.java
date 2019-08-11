package org.sadtech.social.bot.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
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
@Builder
public class AccountAutoCheck {

    @Description("Unut, который обрабатывается при успешной оплате")
    private MainUnit successfulPayment;

    @Description("Unit, который обрабатывается при не успешной оплате")
    private MainUnit failedPayment;

    @Description("Период проверки")
    private Integer periodSec;

    @Description("Время жизни счета")
    private Integer lifeTimeHours;
}

package org.sadtech.social.bot.service.action;

import org.sadtech.social.bot.domain.AccountAutoCheck;
import org.sadtech.social.bot.domain.Timer;
import org.sadtech.social.bot.domain.unit.AnswerAccount;
import org.sadtech.social.bot.domain.unit.AnswerText;
import org.sadtech.social.bot.domain.unit.MainUnit;
import org.sadtech.social.bot.service.timer.TimerService;
import org.sadtech.social.core.domain.BoxAnswer;
import org.sadtech.social.core.domain.content.Mail;
import org.sadtech.social.core.domain.keyboard.button.KeyBoardButtonAccount;
import org.sadtech.social.core.domain.money.Account;
import org.sadtech.social.core.service.AccountService;
import org.sadtech.social.core.utils.KeyBoards;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * Обработчик Unit-а {@link AnswerAccount}.
 *
 * @author upagge [11/07/2019]
 */
public class AnswerAccountAction implements ActionUnit<AnswerAccount, Mail> {

    private final AccountService accountService;
    private TimerService timerService;

    public AnswerAccountAction(AccountService accountService, TimerService timerService) {
        this.accountService = accountService;
        this.timerService = timerService;
    }

    public AnswerAccountAction(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public MainUnit action(AnswerAccount answerAccount, Mail mail) {
        Account account = new Account();
        account.setBelongsPersonId(mail.getPersonId());
        account.setTotalSum(answerAccount.getTotalSum());

        Integer accountId = accountService.add(account);

        settingCheckTimer(answerAccount, mail, accountId);

        KeyBoardButtonAccount buttonAccount = KeyBoardButtonAccount.builder()
                .accountId(accountId)
                .amount(answerAccount.getTotalSum()).build();

        BoxAnswer boxAnswer = BoxAnswer.builder()
                .message("Для оплаты укажите номер счета " + accountId + "\nСумма к оплате: "
                        + answerAccount.getTotalSum())
                .keyBoard(KeyBoards.singelton(buttonAccount))
                .build();

        return AnswerText.builder().boxAnswer(boxAnswer).build();
    }

    private void settingCheckTimer(AnswerAccount answerAccount, Mail mail, Integer accountId) {
        AccountAutoCheck autoCheck = answerAccount.getAutoCheck();
        if (autoCheck != null && timerService != null) {
            Timer timer = Timer.builder()
                    .personId(mail.getPersonId())
                    .unitAnswer(autoCheck.getSuccessfulPayment())
                    .unitDeath(autoCheck.getFailedPayment())
                    .checkLoop(content1 -> accountService.paymentVerification(accountId))
                    .periodSec(autoCheck.getPeriodSec())
                    .timeActive(LocalDateTime
                            .now(Clock.tickSeconds(ZoneId.systemDefault()))
                            .plusSeconds(autoCheck.getPeriodSec()))
                    .timeDeath(LocalDateTime
                            .now(Clock.tickSeconds(ZoneId.systemDefault()))
                            .plusHours(autoCheck.getLifeTimeHours()))
                    .build();
            timerService.add(timer);
        }
    }
}

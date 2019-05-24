package org.sadtech.bot.autoresponder.service.action;

import org.sadtech.bot.autoresponder.domain.unit.AccountAutoCheck;
import org.sadtech.bot.autoresponder.domain.unit.AnswerAccount;
import org.sadtech.bot.autoresponder.domain.unit.AnswerText;
import org.sadtech.bot.autoresponder.domain.unit.MainUnit;
import org.sadtech.bot.autoresponder.timer.Timer;
import org.sadtech.bot.autoresponder.timer.TimerService;
import org.sadtech.bot.core.domain.BoxAnswer;
import org.sadtech.bot.core.domain.Content;
import org.sadtech.bot.core.domain.money.Account;
import org.sadtech.bot.core.service.AccountService;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class AnswerAccountAction implements ActionUnit<AnswerAccount, Content> {

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
    public MainUnit action(AnswerAccount answerAccount, Content content) {
        Account account = new Account();
        account.setBelongsPersonId(content.getPersonId());
        account.setTotalSum(answerAccount.getTotalSum());

        Integer accountId = accountService.add(account);

        AccountAutoCheck autoCheck = answerAccount.getAutoCheck();
        if (autoCheck != null && timerService != null) {
            Timer timer = Timer.builder()
                    .personId(content.getPersonId())
                    .unitAnswer(autoCheck.getSuccessfulPayment())
                    .unitDeath(autoCheck.getFailedPayment())
                    .checkLoop((id, mes) -> accountService.paymentVerification(accountId))
                    .periodSec(autoCheck.getPeriodSec())
                    .timeActive(LocalDateTime.now(Clock.tickSeconds(ZoneId.systemDefault())).plusSeconds(autoCheck.getPeriodSec()))
                    .timeDeath(LocalDateTime.now(Clock.tickSeconds(ZoneId.systemDefault())).plusHours(autoCheck.getLifetimeHours()))
                    .build();
            timerService.add(timer);
        }

        return new AnswerText(BoxAnswer.builder().message("Для оплаты укажите номер счета " + accountId).build());
    }
}

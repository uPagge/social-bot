package org.sadtech.bot.autoresponder.service.action;

import org.sadtech.bot.autoresponder.domain.AccountAutoCheck;
import org.sadtech.bot.autoresponder.domain.Timer;
import org.sadtech.bot.autoresponder.domain.unit.AnswerAccount;
import org.sadtech.bot.autoresponder.domain.unit.AnswerText;
import org.sadtech.bot.autoresponder.domain.unit.MainUnit;
import org.sadtech.bot.autoresponder.service.timer.TimerService;
import org.sadtech.bot.core.domain.BoxAnswer;
import org.sadtech.bot.core.domain.content.Mail;
import org.sadtech.bot.core.domain.keyboard.button.KeyBoardButtonAccount;
import org.sadtech.bot.core.domain.money.Account;
import org.sadtech.bot.core.service.AccountService;
import org.sadtech.bot.core.utils.KeyBoards;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;

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

package org.sadtech.social.bot.service.action;

import org.sadtech.social.bot.GeneralAutoresponder;
import org.sadtech.social.bot.domain.Timer;
import org.sadtech.social.bot.domain.unit.AnswerTimer;
import org.sadtech.social.bot.domain.unit.MainUnit;
import org.sadtech.social.bot.service.timer.TimerActionTask;
import org.sadtech.social.bot.service.timer.TimerService;
import org.sadtech.social.core.domain.content.Message;
import org.sadtech.social.core.exception.TimerSettingException;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;

/**
 * Обработчик Unit-а {@link AnswerTimer}.
 *
 * @author upagge [11/07/2019]
 */
public class AnswerTimerAction implements ActionUnit<AnswerTimer, Message> {

    private TimerService timerService;
    private Long verificationPeriodSec = 15L;

    public AnswerTimerAction(TimerService timerService, GeneralAutoresponder generalAutoresponder) {
        this.timerService = timerService;
        TimerActionTask timerActionTask = new TimerActionTask(timerService, generalAutoresponder);
        java.util.Timer timer = new java.util.Timer(true);
        timer.schedule(timerActionTask, 0, 1000L * verificationPeriodSec);
    }

    public Long getVerificationPeriodSec() {
        return verificationPeriodSec;
    }

    public void setVerificationPeriodSec(Long verificationPeriodSec) {
        this.verificationPeriodSec = verificationPeriodSec;
    }

    @Override
    public MainUnit action(AnswerTimer answerTimer, Message message) {
        LocalDateTime timeActive = LocalDateTime
                .now(Clock.tickSeconds(ZoneId.systemDefault()))
                .plusSeconds(Optional
                        .ofNullable(answerTimer.getTimeDelaySec())
                        .orElseThrow(() -> new TimerSettingException("Не установлена временная задержка таймера")));

        Timer.TimerBuilder timer = Timer.builder()
                .personId(message.getPersonId())
                .unitAnswer(answerTimer.getUnitAnswer())
                .timeActive(timeActive)
                .periodSec(answerTimer.getTimeDelaySec())
                .checkLoop(answerTimer.getCheckLoop());

        if (answerTimer.getTimeDeathSec() != null) {
            timer.timeDeath(LocalDateTime
                    .now(Clock.tickSeconds(ZoneId.systemDefault()))
                    .plusSeconds(answerTimer.getTimeDeathSec()));
        } else if (answerTimer.getCheckLoop() == null) {
            timer.timeDeath(timeActive);
        }

        timerService.add(timer.build());
        return answerTimer;
    }
}

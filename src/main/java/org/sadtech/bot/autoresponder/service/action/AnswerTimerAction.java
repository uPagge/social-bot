package org.sadtech.bot.autoresponder.service.action;

import org.sadtech.bot.autoresponder.GeneralAutoresponder;
import org.sadtech.bot.autoresponder.domain.Timer;
import org.sadtech.bot.autoresponder.domain.unit.AnswerTimer;
import org.sadtech.bot.autoresponder.domain.unit.MainUnit;
import org.sadtech.bot.autoresponder.service.timer.TimerActionTask;
import org.sadtech.bot.autoresponder.service.timer.TimerService;
import org.sadtech.bot.core.domain.content.Message;
import org.sadtech.bot.core.exception.TimerSettingExceprion;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;

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
                        .orElseThrow(() -> new TimerSettingExceprion("Не установлена временная задержка таймера")));

        Timer.Builder timer = Timer.builder()
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

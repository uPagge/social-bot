package org.sadtech.bot.autoresponder.service.action;

import org.sadtech.bot.autoresponder.GeneralAutoresponder;
import org.sadtech.bot.autoresponder.domain.unit.AnswerTimer;
import org.sadtech.bot.autoresponder.domain.unit.MainUnit;
import org.sadtech.bot.autoresponder.timer.Timer;
import org.sadtech.bot.autoresponder.timer.TimerActionTask;
import org.sadtech.bot.autoresponder.timer.TimerService;
import org.sadtech.bot.core.domain.content.Content;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class AnswerTimerAction implements ActionUnit<AnswerTimer, Content> {

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
    public MainUnit action(AnswerTimer answerTimer, Content content) {
        Timer.Builder timer = Timer.builder();
        if (answerTimer.getPersonId() != null) {
            timer.personId(answerTimer.getPersonId());
        } else {
            timer.personId(content.getPersonId());
        }
        timer.unitAnswer(answerTimer.getUnitAnswer())
                .timeActive(LocalDateTime.now(Clock.tickSeconds(ZoneId.systemDefault())).plusSeconds(answerTimer.getTimeDelaySec() * 1000L))
                .periodSec(answerTimer.getTimeDelaySec())
                .checkLoop(answerTimer.getCheckLoop())
                .timeDeath(LocalDateTime.now(Clock.tickSeconds(ZoneId.systemDefault())).plusSeconds(answerTimer.getTimeDeathSec()));
        timerService.add(timer.build());
        return answerTimer;
    }
}

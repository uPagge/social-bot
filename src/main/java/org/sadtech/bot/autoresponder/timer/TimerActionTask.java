package org.sadtech.bot.autoresponder.timer;

import org.sadtech.bot.autoresponder.GeneralAutoresponder;
import org.sadtech.bot.autoresponder.domain.usercode.CheckData;
import org.sadtech.bot.core.domain.Content;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.TimerTask;

public class TimerActionTask extends TimerTask {

    private static final Logger log = LoggerFactory.getLogger(TimerActionTask.class);

    private final TimerService timerService;
    private final GeneralAutoresponder generalAutoresponder;

    public TimerActionTask(TimerService timerService, GeneralAutoresponder generalAutoresponder) {
        this.generalAutoresponder = generalAutoresponder;
        log.info("Инициализация сервиса по активации таймеров");
        this.timerService = timerService;
    }

    @Override
    public void run() {
        LocalDateTime nowDate = LocalDateTime.now(Clock.tickSeconds(ZoneId.systemDefault()));
        log.info("Сервис таймеров сработал. Время: " + nowDate);
        for (Timer timer : timerService.getTimerActive(nowDate)) {
            Content emptyContent = Content.EMPTY_CONTENT;
            emptyContent.setPersonId(timer.getPersonId());
            CheckData checkLoop = timer.getCheckLoop();

            if (!timeDeath(nowDate, timer.getTimeDeath())) {
                if (checkLoop != null) {
                    if (checkLoop.checked(emptyContent.getPersonId(), emptyContent.getMessage())) {
                        generalAutoresponder.answer(emptyContent, timer.getUnitAnswer());
                        timerService.remove(timer.getId());
                    }
                    if (timer.getPeriodSec() != null) {
                        timer.setTimeActive(timer.getTimeActive().plusSeconds(timer.getPeriodSec()));
                        timerService.edit(timer.getId(), timer);
                    }
                } else {
                    generalAutoresponder.answer(emptyContent, timer.getUnitAnswer());
                    if (timer.getPeriodSec() != null) {
                        timer.setTimeActive(timer.getTimeActive().plusSeconds(timer.getPeriodSec()));
                        timerService.edit(timer.getId(), timer);
                    }
                }
            } else {
                death(timer, emptyContent);
            }

        }
    }

    private void death(Timer timer, Content emptyContent) {
        if (timer.getUnitDeath() != null) {
            generalAutoresponder.answer(emptyContent, timer.getUnitDeath());
        }
        timerService.remove(timer.getId());
    }

    private boolean timeDeath(LocalDateTime nowTime, LocalDateTime timeDeath) {
        return timeDeath != null && nowTime.isAfter(timeDeath);
    }

}

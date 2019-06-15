package org.sadtech.bot.autoresponder.service.timer;

import org.sadtech.bot.autoresponder.GeneralAutoresponder;
import org.sadtech.bot.autoresponder.domain.Timer;
import org.sadtech.bot.autoresponder.service.usercode.CheckData;
import org.sadtech.bot.core.domain.content.Message;
import org.sadtech.bot.core.utils.Contents;
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
        log.info("Сервис таймеров сработал. Время: {}", nowDate);

        timerService.getTimerActive(nowDate)
                .parallelStream()
                .forEach(timer -> processingTimer(timer, nowDate));

    }

    private void processingTimer(Timer timer, LocalDateTime nowDate) {
        Message emptyMessage = Contents.EMPTY_CONTENT;
        emptyMessage.setPersonId(timer.getPersonId());
        CheckData checkLoop = timer.getCheckLoop();

        if (!timeDeath(nowDate, timer.getTimeDeath())) {
            if (checkLoop != null) {
                if (checkLoop.checked(emptyMessage)) {
                    generalAutoresponder.answer(emptyMessage, timer.getUnitAnswer());
                    timerService.remove(timer.getId());
                } else {
                    reinstallation(timer);
                }
            } else {
                generalAutoresponder.answer(emptyMessage, timer.getUnitAnswer());
                reinstallation(timer);
            }
        } else {
            generalAutoresponder.answer(emptyMessage, timer.getUnitAnswer());
            death(timer, emptyMessage);
        }
    }

    private void reinstallation(Timer timer) {
        if (timer.getPeriodSec() != null) {
            timer.setTimeActive(timer.getTimeActive().plusSeconds(timer.getPeriodSec()));
            timerService.edit(timer.getId(), timer);
        }
    }

    private void death(Timer timer, Message emptyMessage) {
        if (timer.getUnitDeath() != null) {
            generalAutoresponder.answer(emptyMessage, timer.getUnitDeath());
        }
        timerService.remove(timer.getId());
    }

    private boolean timeDeath(LocalDateTime nowTime, LocalDateTime timeDeath) {
        return timeDeath != null && nowTime.isAfter(timeDeath);
    }

}

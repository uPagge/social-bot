package org.sadtech.social.bot.service.timer;

import lombok.extern.slf4j.Slf4j;
import org.sadtech.social.bot.GeneralAutoResponder;
import org.sadtech.social.bot.domain.Timer;
import org.sadtech.social.bot.service.usercode.CheckData;
import org.sadtech.social.core.domain.content.Message;
import org.sadtech.social.core.utils.MessageUtils;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.TimerTask;

/**
 * Обслуживание таймеров. Отвечает за активацию, удаление, перенастройку таймеров.
 *
 * @author upagge [11/07/2019]
 */
@Slf4j
public class TimerActionTask extends TimerTask {

    private final TimerService timerService;
    private final GeneralAutoResponder generalAutoresponder;

    public TimerActionTask(TimerService timerService, GeneralAutoResponder generalAutoresponder) {
        this.generalAutoresponder = generalAutoresponder;
        log.info("Инициализация сервиса по активации таймеров");
        this.timerService = timerService;
    }

    @Override
    public void run() {
        LocalDateTime nowDate = LocalDateTime.now(Clock.tickSeconds(ZoneId.systemDefault()));
        log.info("Сервис таймеров сработал. Время: {}", nowDate);

        timerService.getTimerActive()
                .parallelStream()
                .forEach(timer -> processingTimer(timer, nowDate));

    }

    private void processingTimer(Timer timer, LocalDateTime nowDate) {
        Message emptyMessage = MessageUtils.EMPTY_MESSAGE;
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

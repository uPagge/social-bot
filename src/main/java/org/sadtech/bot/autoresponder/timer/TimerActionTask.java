package org.sadtech.bot.autoresponder.timer;

import org.sadtech.bot.autoresponder.GeneralAutoresponder;
import org.sadtech.bot.autoresponder.domain.unit.MainUnit;
import org.sadtech.bot.core.domain.Content;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.TimerTask;

public class TimerActionTask extends TimerTask {

    private static final Logger log = LoggerFactory.getLogger(TimerActionTask.class);

    private final TimerActionService timerService;
    private final GeneralAutoresponder generalAutoresponder;

    public TimerActionTask(TimerActionService timerService, GeneralAutoresponder generalAutoresponder) {
        this.generalAutoresponder = generalAutoresponder;
        log.info("Инициализация сервиса по активации таймеров");
        this.timerService = timerService;
    }

    @Override
    public void run() {
        LocalDateTime date = LocalDateTime.now(Clock.tickSeconds(ZoneId.systemDefault()));
        log.info("Сервис таймеров сработал. Время: " + date);
        for (TimerAction timerAction : timerService.getTimerActive(date)) {
            Content content = Content.EMPTY_CONTENT;
            content.setPersonId(timerAction.getPersonId());
            MainUnit unit = timerAction.getUnit();
            generalAutoresponder.answer(content, unit);
            log.info("Таймер удален");
            timerService.remove(timerAction);
        }
    }

}

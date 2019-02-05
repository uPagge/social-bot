package org.sadtech.vkbot.autoresponder.timer;

import org.apache.log4j.Logger;
import org.sadtech.vkbot.autoresponder.timer.impl.TimerAction;
import org.sadtech.vkbot.core.entity.Mail;

import java.util.Date;
import java.util.TimerTask;

public class TimerActionTask extends TimerTask {

    public static final Logger log = Logger.getLogger(TimerActionTask.class);

    private TimerActionService timerService;

    public TimerActionTask(TimerActionService timerService) {
        log.info("Инициализация сервиса по активации таймеров");
        this.timerService = timerService;
    }

    @Override
    public void run() {
        Long date = new Date().getTime();
        log.info("Сервис таймеров сработал. Время: " + date);
        Mail mail = new Mail();
        for (TimerAction timerAction : timerService.getTimerActive(date)) {
            mail.setPeerId(timerAction.getIdPerson());
            timerAction.getActionUnit().action(timerAction.getUnit(), mail);
            if (timerAction.getRepeated()) {
                timerAction.setTimeActive(new Date().getTime() + timerAction.getPeriod());
                log.info("Таймер установлен повторно: " + timerAction);
            } else {
                log.info("Таймер удален");
                timerService.remove(timerAction);
            }
        }
    }
}

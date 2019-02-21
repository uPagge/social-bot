package org.sadtech.vkbot.autoresponder.timer;

import org.apache.log4j.Logger;
import org.sadtech.vkbot.autoresponder.action.Action;
import org.sadtech.vkbot.autoresponder.timer.impl.TimerAction;
import org.sadtech.vkbot.core.entity.Mail;
import org.sadtech.vkbot.core.entity.Person;

import java.util.Date;
import java.util.TimerTask;

public class TimerActionTask extends TimerTask {

    public static final Logger log = Logger.getLogger(TimerActionTask.class);

    private TimerActionService timerService;
    private Action action;

    public TimerActionTask(Action action, TimerActionService timerService) {
        log.info("Инициализация сервиса по активации таймеров");
        this.timerService = timerService;
        this.action = action;
    }

    @Override
    public void run() {
        Long date = new Date().getTime();
        log.info("Сервис таймеров сработал. Время: " + date);
        Mail mail = new Mail();
        Person person = new Person();
        mail.setPerson(person);
        for (TimerAction timerAction : timerService.getTimerActive(date)) {
            person.setId(timerAction.getIdPerson());
            mail.setPeerId(timerAction.getIdPerson());
            action.action(timerAction.getUnit(), mail.getBody(), mail.getPerson().getId());
            log.info("Таймер удален");
            timerService.remove(timerAction);
        }
    }

}

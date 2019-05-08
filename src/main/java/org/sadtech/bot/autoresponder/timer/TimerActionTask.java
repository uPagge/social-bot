package org.sadtech.bot.autoresponder.timer;

import org.apache.log4j.Logger;
import org.sadtech.bot.autoresponder.domain.unit.TypeUnit;
import org.sadtech.bot.autoresponder.service.action.ActionUnit;

import java.util.Date;
import java.util.Map;
import java.util.TimerTask;

public class TimerActionTask extends TimerTask {

    private static final Logger log = Logger.getLogger(TimerActionTask.class);

    private TimerActionService timerService;
    private Map<TypeUnit, ActionUnit> actionUnitMap;

    public TimerActionTask(TimerActionService timerService, Map<TypeUnit, ActionUnit> actionUnitMap) {
        log.info("Инициализация сервиса по активации таймеров");
        this.timerService = timerService;
        this.actionUnitMap = actionUnitMap;
    }

    @Override
    public void run() {
        Long date = new Date().getTime();
        log.info("Сервис таймеров сработал. Время: " + date);
//        for (TimerAction timerAction : timerService.getTimerActive(date)) {
//            new Content();
//            actionUnitMap.get(timerAction.getUnit().getTypeUnit()).action(timerAction.getUnit(), null, timerAction.getIdPerson());
//            log.info("Таймер удален");
//            timerService.remove(timerAction);
//        }
    }

}

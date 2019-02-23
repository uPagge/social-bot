package org.sadtech.vkbot.autoresponder.timer;

import org.apache.log4j.Logger;
import org.sadtech.vkbot.autoresponder.action.ActionUnit;
import org.sadtech.vkbot.autoresponder.domain.unit.TypeUnit;

import java.util.Date;
import java.util.Map;
import java.util.TimerTask;

public class TimerActionTask extends TimerTask {

    public static final Logger log = Logger.getLogger(TimerActionTask.class);

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
        for (TimerAction timerAction : timerService.getTimerActive(date)) {
            actionUnitMap.get(timerAction.getUnit().getTypeUnit()).action(timerAction.getUnit(), null, timerAction.getIdPerson());
            log.info("Таймер удален");
            timerService.remove(timerAction);
        }
    }

}

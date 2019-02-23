package org.sadtech.vkbot.autoresponder.action.impl;

import org.apache.log4j.Logger;
import org.sadtech.vkbot.autoresponder.action.ActionUnit;
import org.sadtech.vkbot.autoresponder.entity.unit.TimerAnswer;
import org.sadtech.vkbot.autoresponder.entity.unit.TypeUnit;
import org.sadtech.vkbot.autoresponder.timer.TimerActionService;
import org.sadtech.vkbot.autoresponder.timer.TimerActionTask;
import org.sadtech.vkbot.autoresponder.timer.impl.TimerAction;

import java.util.Date;
import java.util.Map;
import java.util.Timer;

public class TimerAnswerAction implements ActionUnit<TimerAnswer> {

    public static final Logger log = Logger.getLogger(TimerAnswerAction.class);

    private TimerActionService timerService;
    private Integer verificationPeriodSec = 30;

    public TimerAnswerAction(TimerActionService timerService, Map<TypeUnit, ActionUnit> actionUnitMap) {
        this.timerService = timerService;

        TimerActionTask timerActionTask = new TimerActionTask(timerService, actionUnitMap);
        Timer timer = new Timer(true);
        timer.schedule(timerActionTask, 0, 1000 * verificationPeriodSec);
    }

    public Integer getVerificationPeriodSec() {
        return verificationPeriodSec;
    }

    public void setVerificationPeriodSec(Integer verificationPeriodSec) {
        this.verificationPeriodSec = verificationPeriodSec;
    }

    @Override
    public void action(TimerAnswer timerAnswer, String message, Integer idPerson) {
        TimerAction timerAction = new TimerAction();
        if (timerAnswer.getIdUser() != null) {
            timerAction.setIdPerson(timerAnswer.getIdUser());
        } else {
            timerAction.setIdPerson(idPerson);
        }
        timerAction.setUnit(timerAnswer.getUnitAnswer());
        timerAction.setTimeActive(new Date().getTime() + timerAnswer.getTimeDelaySec() * 1000);
        log.info("Таймер установлен: " + timerAction);
        timerService.add(timerAction);
    }
}

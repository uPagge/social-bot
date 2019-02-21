package org.sadtech.vkbot.autoresponder.action.impl;

import org.apache.log4j.Logger;
import org.sadtech.autoresponder.entity.Unit;
import org.sadtech.vkbot.autoresponder.action.Action;
import org.sadtech.vkbot.autoresponder.action.ActionUnit;
import org.sadtech.vkbot.autoresponder.entity.unit.TimerAnswer;
import org.sadtech.vkbot.autoresponder.timer.TimerActionService;
import org.sadtech.vkbot.autoresponder.timer.TimerActionTask;
import org.sadtech.vkbot.autoresponder.timer.impl.TimerAction;
import org.sadtech.vkbot.autoresponder.timer.impl.TimerActionRepositoryList;
import org.sadtech.vkbot.autoresponder.timer.impl.TimerActionServiceImpl;

import java.util.Date;
import java.util.Timer;

public class TimerAnswerAction implements ActionUnit {

    public static final Logger log = Logger.getLogger(TimerAnswerAction.class);

    private TimerActionService timerService;
    private Integer verificationPeriodSec = 30;

    public TimerAnswerAction(Action action, TimerActionService timerService) {
        action.registerActionUnit(TimerAnswer.class, this);
        this.timerService = timerService;

        TimerActionTask timerActionTask = new TimerActionTask(action, timerService);
        Timer timer = new Timer(true);
        timer.schedule(timerActionTask, 0, 1000 * verificationPeriodSec);
    }

    public TimerAnswerAction(Action action) {
        action.registerActionUnit(TimerAnswer.class, this);
        this.timerService = new TimerActionServiceImpl(new TimerActionRepositoryList());

        TimerActionTask timerActionTask = new TimerActionTask(action, timerService);
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
    public void action(Unit unit, String message, Integer idPerson) {
        TimerAnswer timerAnswer = (TimerAnswer) unit;
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

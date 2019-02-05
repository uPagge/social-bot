package org.sadtech.vkbot.autoresponder.action.impl;

import org.apache.log4j.Logger;
import org.sadtech.autoresponder.entity.Unit;
import org.sadtech.vkbot.autoresponder.action.Action;
import org.sadtech.vkbot.autoresponder.action.ActionUnit;
import org.sadtech.vkbot.autoresponder.entity.TimerAnswer;
import org.sadtech.vkbot.autoresponder.service.ActionService;
import org.sadtech.vkbot.autoresponder.timer.TimerActionService;
import org.sadtech.vkbot.autoresponder.timer.TimerActionTask;
import org.sadtech.vkbot.autoresponder.timer.impl.TimerAction;
import org.sadtech.vkbot.core.entity.Mail;

import java.util.Date;
import java.util.Timer;

public class TimerAnswerAction implements ActionUnit {

    public static final Logger log = Logger.getLogger(TimerAnswerAction.class);

    private ActionService actionService;
    private TimerActionService timerService;
    private Action action;
    private Integer verificationPeriodSec = 10;

    public TimerAnswerAction() {

    }

    public TimerAnswerAction(Action action, TimerActionService timerService) {
        action.registerActionUnit(TimerAnswer.class, this);
        this.timerService = timerService;
        actionService = action.getActionService();
        TimerActionTask timerActionTask = new TimerActionTask(timerService);
        Timer timer = new Timer(true);
        timer.schedule(timerActionTask, 0, 1000 * verificationPeriodSec);
    }

    @Override
    public void action(Unit unit, Mail mail) {
        TimerAnswer timerAnswer = (TimerAnswer) unit;
        TimerAction timerAction = new TimerAction();
        if (timerAnswer.getIdUser() != null) {
            timerAction.setIdPerson(timerAnswer.getIdUser());
        } else {
            timerAction.setIdPerson(mail.getPeerId());
        }
        timerAction.setUnit(timerAnswer.getUnit());
        timerAction.setTimeActive(new Date().getTime() + timerAnswer.getTimeDelaySec() * 1000);
        timerAction.setActionUnit(actionService.get(timerAnswer.getUnit().getClass()));
        timerAnswer.getNextUnits().forEach(nextUnit -> {
            if (nextUnit.equals(timerAnswer)) {
                timerAction.setRepeated(true);
                timerAction.setPeriod(timerAnswer.getTimeDelaySec() * 1000);
            }
        });
        log.info("Таймер установлен: " + timerAction);
        timerService.add(timerAction);
    }

    public Integer getVerificationPeriodSec() {
        return verificationPeriodSec;
    }

    public void setVerificationPeriodSec(Integer verificationPeriodSec) {
        this.verificationPeriodSec = verificationPeriodSec;
    }
}

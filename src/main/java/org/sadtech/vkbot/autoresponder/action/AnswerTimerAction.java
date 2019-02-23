package org.sadtech.vkbot.autoresponder.action;

import org.apache.log4j.Logger;
import org.sadtech.vkbot.autoresponder.entity.unit.AnswerTimer;
import org.sadtech.vkbot.autoresponder.entity.unit.TypeUnit;
import org.sadtech.vkbot.autoresponder.timer.TimerActionService;
import org.sadtech.vkbot.autoresponder.timer.TimerActionTask;
import org.sadtech.vkbot.autoresponder.timer.TimerAction;

import java.util.Date;
import java.util.Map;
import java.util.Timer;

public class AnswerTimerAction implements ActionUnit<AnswerTimer> {

    public static final Logger log = Logger.getLogger(AnswerTimerAction.class);

    private TimerActionService timerService;
    private Integer verificationPeriodSec = 30;

    public AnswerTimerAction(TimerActionService timerService, Map<TypeUnit, ActionUnit> actionUnitMap) {
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
    public void action(AnswerTimer answerTimer, String message, Integer idPerson) {
        TimerAction timerAction = new TimerAction();
        if (answerTimer.getIdUser() != null) {
            timerAction.setIdPerson(answerTimer.getIdUser());
        } else {
            timerAction.setIdPerson(idPerson);
        }
        timerAction.setUnit(answerTimer.getUnitAnswer());
        timerAction.setTimeActive(new Date().getTime() + answerTimer.getTimeDelaySec() * 1000);
        log.info("Таймер установлен: " + timerAction);
        timerService.add(timerAction);
    }
}

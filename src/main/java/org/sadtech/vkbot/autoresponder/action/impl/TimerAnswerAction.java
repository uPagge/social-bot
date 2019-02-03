package org.sadtech.vkbot.autoresponder.action.impl;

import org.apache.log4j.Logger;
import org.sadtech.autoresponder.entity.Unit;
import org.sadtech.vkbot.autoresponder.action.Action;
import org.sadtech.vkbot.autoresponder.action.ActionUnit;
import org.sadtech.vkbot.autoresponder.entity.TimerAnswer;
import org.sadtech.vkbot.autoresponder.service.ActionService;
import org.sadtech.vkbot.autoresponder.timer.TimerVk;
import org.sadtech.vkbot.core.entity.Mail;

import java.util.Timer;

public class TimerAnswerAction implements ActionUnit {

    public static final Logger log = Logger.getLogger(TimerAnswerAction.class);

    private ActionService actionService;
    private Action action;

    public TimerAnswerAction(Action action) {
        action.registerActionUnit(TimerAnswer.class, this);
        actionService = action.getActionService();
    }

    @Override
    public void action(Unit unit, Mail mail) {
        log.info("Установка таймера");
        TimerAnswer timerAnswer = (TimerAnswer) unit;
        TimerVk timerVk = new TimerVk(actionService.get(timerAnswer.getUnit().getClass()));
        timerVk.setIdPerson(mail.getPerson().getId());
        timerVk.setUnit(timerAnswer.getUnit());
        Timer timer = new Timer(true);
        timer.schedule(timerVk, 10*1000);
    }
}

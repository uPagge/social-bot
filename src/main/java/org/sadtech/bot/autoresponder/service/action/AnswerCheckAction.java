package org.sadtech.bot.autoresponder.service.action;

import org.sadtech.bot.autoresponder.domain.unit.AnswerCheck;
import org.sadtech.bot.autoresponder.domain.unit.MainUnit;
import org.sadtech.bot.core.domain.content.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AnswerCheckAction implements ActionUnit<AnswerCheck, Message> {

    private static final Logger log = LoggerFactory.getLogger(AnswerCheckAction.class);

    @Override
    public MainUnit action(AnswerCheck answerCheck, Message mail) {
        MainUnit unitAnswer;
        if (answerCheck.getCheck().checked(mail)) {
            log.info("Проверка пройдена");
            unitAnswer = answerCheck.getUnitTrue();
        } else {
            log.info("Проверка не пройдена");
            unitAnswer = answerCheck.getUnitFalse();
        }
        if (unitAnswer != null) {
            return unitAnswer;
        } else {
            return answerCheck;
        }
    }

}

package org.sadtech.social.bot.service.action;

import org.sadtech.social.bot.domain.unit.AnswerCheck;
import org.sadtech.social.bot.domain.unit.MainUnit;
import org.sadtech.social.core.domain.content.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Обработчик Unit-а {@link AnswerCheck}.
 *
 * @author upagge [11/07/2019]
 */
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

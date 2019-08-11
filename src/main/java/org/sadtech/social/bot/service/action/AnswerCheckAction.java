package org.sadtech.social.bot.service.action;

import lombok.extern.slf4j.Slf4j;
import org.sadtech.social.bot.domain.unit.AnswerCheck;
import org.sadtech.social.bot.domain.unit.MainUnit;
import org.sadtech.social.core.domain.content.Message;

import java.util.Optional;

/**
 * Обработчик Unit-а {@link AnswerCheck}.
 *
 * @author upagge [11/07/2019]
 */
@Slf4j
public class AnswerCheckAction implements ActionUnit<AnswerCheck, Message> {

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
        return Optional.ofNullable(unitAnswer).orElse(answerCheck);
    }

}

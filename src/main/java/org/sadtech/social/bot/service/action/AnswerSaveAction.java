package org.sadtech.social.bot.service.action;

import org.sadtech.social.bot.domain.unit.AnswerSave;
import org.sadtech.social.bot.domain.unit.MainUnit;
import org.sadtech.social.bot.service.save.Preservable;
import org.sadtech.social.core.domain.content.Message;

/**
 * Обработчик Unit-а {@link AnswerSave}.
 *
 * @author upagge [11/07/2019]
 */
public class AnswerSaveAction implements ActionUnit<AnswerSave, Message> {

    @Override
    public MainUnit action(AnswerSave answerSave, Message mail) {
        Preservable preservable = answerSave.getPreservable();
        Integer personId = mail.getPersonId();

        Object data = answerSave.getPreservableData().getData(mail);
        if (data != null) {
            preservable.save(personId, data);
        }


        preservable.push(personId, answerSave.getPusher());
        return answerSave;
    }
}

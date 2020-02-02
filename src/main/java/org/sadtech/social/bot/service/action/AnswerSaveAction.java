package org.sadtech.social.bot.service.action;

import org.sadtech.social.bot.domain.unit.AnswerSave;
import org.sadtech.social.bot.domain.unit.MainUnit;
import org.sadtech.social.bot.service.save.CheckSave;
import org.sadtech.social.bot.service.save.Preservable;
import org.sadtech.social.bot.service.save.data.PreservableData;
import org.sadtech.social.core.domain.content.Message;

/**
 * Обработчик Unit-а {@link AnswerSave}.
 *
 * @author upagge [11/07/2019]
 */
public class AnswerSaveAction<D> implements ActionUnit<AnswerSave<D>, Message> {

    @Override
    public MainUnit action(AnswerSave<D> answerSave, Message mail) {
        Preservable<D> preservable = answerSave.getPreservable();
        Long personId = mail.getPersonId();

        CheckSave<? super Message> checkSave = answerSave.getCheckSave();
        if (checkSave != null) {
            MainUnit unit = checkSave.check(mail);
            if (unit != null) {
                return unit;
            }
        }

        PreservableData<D, ? super Message> preservableData = answerSave.getPreservableData();
        D data = preservableData.getData(mail);
        if (data != null) {
            preservable.save(personId, data);
        }

        preservable.push(personId, answerSave.getPusher());
        return answerSave;
    }
}

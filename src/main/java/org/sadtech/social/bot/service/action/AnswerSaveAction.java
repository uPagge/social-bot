package org.sadtech.social.bot.service.action;

import org.sadtech.social.bot.domain.unit.AnswerSave;
import org.sadtech.social.bot.domain.unit.MainUnit;
import org.sadtech.social.bot.service.save.Preservable;
import org.sadtech.social.bot.service.save.SaveType;
import org.sadtech.social.core.domain.content.Message;

import java.util.Set;

/**
 * Обработчик Unit-а {@link AnswerSave}.
 *
 * @author upagge [11/07/2019]
 */
public class AnswerSaveAction implements ActionUnit<AnswerSave, Message> {

    @Override
    public MainUnit action(AnswerSave answerSave, Message mail) {
        Set<SaveType> unitSaveTypes = answerSave.getSaveTypes();
        Preservable preservable = answerSave.getPreservable();
        Integer personId = mail.getPersonId();
        if (unitSaveTypes.contains(SaveType.INIT) || unitSaveTypes.contains(SaveType.FULL)) {
            preservable.init(personId);
        }

        if (unitSaveTypes.contains(SaveType.SAVE) || unitSaveTypes.contains(SaveType.FULL)) {
            preservable.save(personId, answerSave.getPreservableData().getData(mail));

        }

        if (unitSaveTypes.contains(SaveType.PUSH) || unitSaveTypes.contains(SaveType.FULL)) {
            preservable.push(personId, answerSave.getPusher());
        }
        return answerSave;
    }
}

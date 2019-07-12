package org.sadtech.social.bot.service.action;

import javafx.util.Pair;
import org.sadtech.social.bot.domain.unit.AnswerSave;
import org.sadtech.social.bot.domain.unit.MainUnit;
import org.sadtech.social.bot.service.save.Savable;
import org.sadtech.social.bot.service.save.SaveType;
import org.sadtech.social.bot.service.usercode.SaveData;
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
        Savable savable = answerSave.getSavable();
        if (unitSaveTypes.contains(SaveType.INIT) || unitSaveTypes.contains(SaveType.FULL)) {
            savable.init(mail.getPersonId());
        }

        if (unitSaveTypes.contains(SaveType.SAVE) || unitSaveTypes.contains(SaveType.FULL)) {
            SaveData saveData = answerSave.getSaveData();
            if (saveData != null) {
                savable.save(mail.getPersonId(), saveData.save(mail));
            } else {
                savable.save(mail.getPersonId(), new Pair<>(answerSave.getKey(), mail.getText()));
            }
        }

        if (unitSaveTypes.contains(SaveType.FINISH) || unitSaveTypes.contains(SaveType.FULL)) {
            savable.push(mail.getPersonId());
        }
        return answerSave;
    }
}

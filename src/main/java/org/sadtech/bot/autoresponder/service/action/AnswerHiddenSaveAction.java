package org.sadtech.bot.autoresponder.service.action;

import javafx.util.Pair;
import org.apache.logging.log4j.util.Strings;
import org.sadtech.bot.autoresponder.domain.unit.AnswerHiddenSave;
import org.sadtech.bot.autoresponder.domain.unit.MainUnit;
import org.sadtech.bot.core.domain.Content;

public class AnswerHiddenSaveAction implements ActionUnit<AnswerHiddenSave, Content> {

    @Override
    public MainUnit action(AnswerHiddenSave answerHiddenSave, Content content) {
        if (answerHiddenSave.getSavable() != null && answerHiddenSave.getProcessingData() != null) {
            String processingResult = answerHiddenSave.getProcessingData().processing(content.getPersonId(), Strings.EMPTY);
            answerHiddenSave.getSavable().save(content.getPersonId(), new Pair<>(answerHiddenSave.getKey(), processingResult));
        }
        return answerHiddenSave;
    }

}

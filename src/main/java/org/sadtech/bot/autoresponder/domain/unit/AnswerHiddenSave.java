package org.sadtech.bot.autoresponder.domain.unit;

import org.sadtech.bot.autoresponder.domain.usercode.ProcessingData;
import org.sadtech.bot.autoresponder.saver.Savable;

import java.util.Objects;

public class AnswerHiddenSave extends AnswerSave {

    private final ProcessingData processingData;

    public AnswerHiddenSave(String key, Savable savable, ProcessingData processingData) {
        super(key, savable);
        this.processingData = processingData;
        typeUnit = TypeUnit.HIDDEN_SAVE;
        activeStatus = UnitActiveStatus.AFTER;
    }

    public ProcessingData getProcessingData() {
        return processingData;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AnswerHiddenSave)) return false;
        if (!super.equals(o)) return false;
        AnswerHiddenSave that = (AnswerHiddenSave) o;
        return Objects.equals(processingData, that.processingData);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), processingData);
    }
}

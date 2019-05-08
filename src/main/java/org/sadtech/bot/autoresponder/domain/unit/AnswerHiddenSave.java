package org.sadtech.bot.autoresponder.domain.unit;

import org.sadtech.bot.autoresponder.domain.usercode.ProcessingData;

import java.util.Objects;

public class AnswerHiddenSave extends AnswerSave {

    private ProcessingData processingData;

    public AnswerHiddenSave() {
        typeUnit = TypeUnit.HIDDEN_SAVE;
        unitActiveStatus = UnitActiveStatus.AFTER;
    }

    public ProcessingData getProcessingData() {
        return processingData;
    }

    public void setProcessingData(ProcessingData processingData) {
        this.processingData = processingData;
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

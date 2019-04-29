package org.sadtech.bot.autoresponder.domain.unit;

import org.sadtech.bot.autoresponder.domain.usercode.ProcessingData;

import java.util.Objects;

public class AnswerProcessing extends MainUnit {

    private ProcessingData processingData;

    public AnswerProcessing() {
        typeUnit = TypeUnit.PROCESSING;
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
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        AnswerProcessing that = (AnswerProcessing) o;
        return Objects.equals(processingData, that.processingData);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), processingData);
    }
}

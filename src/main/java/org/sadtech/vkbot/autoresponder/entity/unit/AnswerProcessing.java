package org.sadtech.vkbot.autoresponder.entity.unit;

import org.sadtech.vkbot.autoresponder.entity.usercode.ProcessingData;

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

}

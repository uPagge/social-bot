package org.sadtech.bot.autoresponder.domain.unit;

import org.sadtech.bot.autoresponder.service.usercode.ProcessingData;
import org.sadtech.bot.core.service.sender.Sent;

import java.util.Objects;

public class AnswerProcessing extends MainUnit {

    private ProcessingData processingData;
    private Sent sent;

    public AnswerProcessing() {
        typeUnit = TypeUnit.PROCESSING;
    }

    public ProcessingData getProcessingData() {
        return processingData;
    }

    public Sent getSent() {
        return sent;
    }

    public static Builder builder() {
        return new AnswerProcessing().new Builder();
    }

    public class Builder {
        private Builder() {

        }

        public Builder processingData(ProcessingData processingData) {
            AnswerProcessing.this.processingData = processingData;
            return this;
        }

        public Builder sent(Sent sent) {
            AnswerProcessing.this.sent = sent;
            return this;
        }

        public AnswerProcessing build() {
            return AnswerProcessing.this;
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AnswerProcessing)) return false;
        if (!super.equals(o)) return false;
        AnswerProcessing that = (AnswerProcessing) o;
        return Objects.equals(processingData, that.processingData) &&
                Objects.equals(sent, that.sent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), processingData, sent);
    }

    @Override
    public String toString() {
        return "AnswerProcessing{" +
                "processingData=" + processingData +
                ", sent=" + sent +
                ", activeStatus=" + activeStatus +
                ", typeUnit=" + typeUnit +
                '}';
    }
}

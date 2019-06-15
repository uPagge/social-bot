package org.sadtech.bot.autoresponder.domain.unit;

import org.sadtech.bot.autoresponder.service.usercode.Insert;
import org.sadtech.bot.core.domain.BoxAnswer;
import org.sadtech.bot.core.service.sender.Sent;

import java.util.Objects;

public class AnswerText extends MainUnit {

    private BoxAnswer boxAnswer;
    private Insert insert;
    private Sent sent;

    public AnswerText() {
        typeUnit = TypeUnit.TEXT;
    }

    public BoxAnswer getBoxAnswer() {
        return boxAnswer;
    }

    public Insert getInsert() {
        return insert;
    }

    public Sent getSent() {
        return sent;
    }

    public static Builder builder() {
        return new AnswerText().new Builder();
    }

    public class Builder {
        private Builder() {

        }

        public Builder boxAnswer(BoxAnswer boxAnswer) {
            AnswerText.this.boxAnswer = boxAnswer;
            return this;
        }

        public Builder sent(Sent sent) {
            AnswerText.this.sent = sent;
            return this;
        }

        public Builder insert(Insert insert) {
            AnswerText.this.insert = insert;
            return this;
        }

        public AnswerText build() {
            return AnswerText.this;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AnswerText)) return false;
        if (!super.equals(o)) return false;
        AnswerText that = (AnswerText) o;
        return Objects.equals(boxAnswer, that.boxAnswer) &&
                Objects.equals(insert, that.insert) &&
                Objects.equals(sent, that.sent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), boxAnswer, insert, sent);
    }

    @Override
    public String toString() {
        return "AnswerText{" +
                "boxAnswer=" + boxAnswer +
                ", insert=" + insert +
                ", sent=" + sent +
                '}';
    }
}

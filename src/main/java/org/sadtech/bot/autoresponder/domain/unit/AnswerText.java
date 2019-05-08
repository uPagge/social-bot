package org.sadtech.bot.autoresponder.domain.unit;

import org.sadtech.bot.autoresponder.domain.usercode.Insert;
import org.sadtech.bot.core.domain.BoxAnswer;

import java.util.Objects;

public class AnswerText extends MainUnit {

    private BoxAnswer boxAnswer = new BoxAnswer();
    private Insert insert;

    public AnswerText() {
        super();
        typeUnit = TypeUnit.TEXT;
    }

    public AnswerText(MainUnit... mainUnit) {
        super(mainUnit);
    }

    public BoxAnswer getBoxAnswer() {
        return boxAnswer;
    }

    public Insert getInsert() {
        return insert;
    }

    public void setInsert(Insert insert) {
        this.insert = insert;
    }

    public static Builder builder() {
        return new AnswerText().new Builder();
    }

    public class Builder extends MainUnit {

        private Builder() {

        }

        public Builder message(String message) {
            AnswerText.this.getBoxAnswer().setMessage(message);
            return this;
        }

        public Builder nextUnit(MainUnit mainUnit) {
            AnswerText.this.setNextUnit(mainUnit);
            return this;
        }

        public AnswerText build() {
            return AnswerText.this;
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        AnswerText that = (AnswerText) o;
        return Objects.equals(boxAnswer, that.boxAnswer) &&
                Objects.equals(insert, that.insert);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), boxAnswer, insert);
    }
}

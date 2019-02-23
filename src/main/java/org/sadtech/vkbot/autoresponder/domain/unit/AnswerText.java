package org.sadtech.vkbot.autoresponder.domain.unit;

import org.sadtech.vkbot.core.domain.BoxAnswer;
import org.sadtech.vkbot.autoresponder.domain.usercode.Insert;

import java.util.Objects;

public class AnswerText extends MainUnit {

    private BoxAnswer boxAnswer = new BoxAnswer();
    private Insert insert;

    public AnswerText() {
        typeUnit = TypeUnit.TEXT;
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

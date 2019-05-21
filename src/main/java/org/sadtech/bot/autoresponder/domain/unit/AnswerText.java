package org.sadtech.bot.autoresponder.domain.unit;

import org.sadtech.bot.autoresponder.domain.usercode.Insert;
import org.sadtech.bot.core.domain.BoxAnswer;

import java.util.Objects;

public class AnswerText extends MainUnit {

    private final BoxAnswer boxAnswer;
    private Insert insert;

    public AnswerText(BoxAnswer boxAnswer) {
        typeUnit = TypeUnit.TEXT;
        this.boxAnswer = boxAnswer;
    }

    public BoxAnswer getBoxAnswer() {
        return boxAnswer;
    }

    public void setInsert(Insert insert) {
        this.insert = insert;
    }

    public Insert getInsert() {
        return insert;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AnswerText)) return false;
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

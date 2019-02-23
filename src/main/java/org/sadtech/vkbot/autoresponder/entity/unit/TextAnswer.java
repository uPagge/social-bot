package org.sadtech.vkbot.autoresponder.entity.unit;

import org.sadtech.vkbot.core.entity.BoxAnswer;
import org.sadtech.vkbot.autoresponder.entity.usercode.Insert;

import java.util.Objects;

public class TextAnswer extends MainUnit {

    private BoxAnswer boxAnswer = new BoxAnswer();
    private Insert insert;

    public TextAnswer() {
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
        TextAnswer that = (TextAnswer) o;
        return Objects.equals(boxAnswer, that.boxAnswer) &&
                Objects.equals(insert, that.insert);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), boxAnswer, insert);
    }
}

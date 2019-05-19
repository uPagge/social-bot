package org.sadtech.bot.autoresponder.domain.quiz;

import org.sadtech.bot.core.domain.BoxAnswer;

import java.util.Objects;

public class QuizResult {
    private Integer fromValue;
    private Integer toValue;
    private BoxAnswer boxAnswer;

    public QuizResult(Integer fromValue, Integer toValue, BoxAnswer boxAnswer) {
        this.fromValue = fromValue;
        this.toValue = toValue;
        this.boxAnswer = boxAnswer;
    }

    public Integer getFromValue() {
        return fromValue;
    }

    public Integer getToValue() {
        return toValue;
    }

    public BoxAnswer getBoxAnswer() {
        return boxAnswer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof QuizResult)) return false;
        QuizResult that = (QuizResult) o;
        return Objects.equals(fromValue, that.fromValue) &&
                Objects.equals(toValue, that.toValue) &&
                Objects.equals(boxAnswer, that.boxAnswer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fromValue, toValue, boxAnswer);
    }

    @Override
    public String toString() {
        return "QuizResult{" +
                "fromValue=" + fromValue +
                ", toValue=" + toValue +
                ", boxAnswer=" + boxAnswer +
                '}';
    }
}

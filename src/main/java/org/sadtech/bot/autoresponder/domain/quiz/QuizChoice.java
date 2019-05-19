package org.sadtech.bot.autoresponder.domain.quiz;

import java.util.Objects;

public class QuizChoice {

    private final String text;
    private final Integer value;

    public QuizChoice(String text, Integer value) {
        this.text = text;
        this.value = value;
    }

    public String getText() {
        return text;
    }

    public Integer getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof QuizChoice)) return false;
        QuizChoice that = (QuizChoice) o;
        return Objects.equals(text, that.text) &&
                Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text, value);
    }

    @Override
    public String toString() {
        return "QuizChoice{" +
                "text='" + text + '\'' +
                ", value=" + value +
                '}';
    }
}

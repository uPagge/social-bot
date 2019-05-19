package org.sadtech.bot.autoresponder.domain.unit;

import org.sadtech.bot.autoresponder.domain.quiz.QuizChoice;
import org.sadtech.bot.core.domain.BoxAnswer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AnswerQuiz extends MainUnit {

    private BoxAnswer boxAnswer;
    private List<QuizChoice> answers;

    public BoxAnswer getBoxAnswer() {
        return boxAnswer;
    }

    public void setBoxAnswer(BoxAnswer boxAnswer) {
        this.boxAnswer = boxAnswer;
    }

    public List<QuizChoice> getAnswers() {
        return answers;
    }

    public void setAnswers(List<QuizChoice> answers) {
        if (this.answers == null) {
            this.answers = new ArrayList<>();
        }
        this.answers.addAll(answers);
    }

    public void setAnswers(QuizChoice... answers) {
        if (this.answers == null) {
            this.answers = new ArrayList<>();
        }
        this.answers.addAll(Arrays.asList(answers));
    }
}

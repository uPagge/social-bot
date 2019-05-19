package org.sadtech.bot.autoresponder.saver;

import org.sadtech.bot.autoresponder.domain.quiz.QuizChoice;
import org.sadtech.bot.autoresponder.domain.quiz.QuizResult;

import java.util.Optional;
import java.util.Set;

public class QuizSavable extends LocalSavable<QuizChoice> {

    private Set<QuizResult> quizResults;

    public Set<QuizResult> getQuizResults() {
        return quizResults;
    }

    public void setQuizResults(Set<QuizResult> quizResults) {
        this.quizResults = quizResults;
    }

    @Override
    public void push(Integer personId) {
        Integer result = saveMap.get(personId).stream().mapToInt(QuizChoice::getValue).sum();
        Optional<QuizResult> firstResult = quizResults.stream().filter(quizResult -> quizResult.getFromValue() <= result && quizResult.getToValue() < result).findFirst();
        sent.send(personId, firstResult.get().getBoxAnswer());
    }
}

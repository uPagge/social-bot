package org.sadtech.vkbot.autoresponder.repository;

import org.sadtech.autoresponder.repository.UnitRepository;
import org.sadtech.vkbot.autoresponder.entity.TextAnswer;

import java.util.ArrayList;
import java.util.List;

public class TextAnswerRepository implements UnitRepository<TextAnswer> {

    private List<TextAnswer> textAnswers;

    public TextAnswerRepository() {
        textAnswers = new ArrayList<>();
    }

    public TextAnswerRepository(List<TextAnswer> textAnswers) {
        this.textAnswers = textAnswers;
    }

    @Override
    public void addUnit(TextAnswer unit) {
        textAnswers.add(unit);
    }

    @Override
    public void addUnits(List<TextAnswer> units) {
        textAnswers.addAll(units);
    }

    @Override
    public List<TextAnswer> menuUnits() {
        return textAnswers;
    }
}

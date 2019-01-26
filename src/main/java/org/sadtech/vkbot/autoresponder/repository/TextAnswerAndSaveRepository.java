package org.sadtech.vkbot.autoresponder.repository;

import org.sadtech.autoresponder.repository.UnitRepository;
import org.sadtech.vkbot.autoresponder.entity.TextAnswerAndSave;

import java.util.ArrayList;
import java.util.List;

public class TextAnswerAndSaveRepository implements UnitRepository<TextAnswerAndSave> {

    private List<TextAnswerAndSave> textAnswerAndSaves = new ArrayList<>();

    @Override
    public void addUnit(TextAnswerAndSave unit) {
        textAnswerAndSaves.add(unit);
    }

    @Override
    public void addUnits(List<TextAnswerAndSave> units) {
        textAnswerAndSaves.addAll(units);
    }

    @Override
    public List<TextAnswerAndSave> menuUnits() {
        return textAnswerAndSaves;
    }
}

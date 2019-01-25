package org.sadtech.vkbot.autoresponder.repository;

import org.sadtech.autoresponder.repository.UnitRepository;
import org.sadtech.vkbot.autoresponder.entity.TextAndKeyBoardAnswer;

import java.util.List;

public class TextAndKeyBoardAnswerRepository implements UnitRepository<TextAndKeyBoardAnswer> {

    @Override
    public void addUnit(TextAndKeyBoardAnswer unit) {

    }

    @Override
    public void addUnits(List<TextAndKeyBoardAnswer> units) {

    }

    @Override
    public List<TextAndKeyBoardAnswer> menuUnits() {
        return null;
    }
}

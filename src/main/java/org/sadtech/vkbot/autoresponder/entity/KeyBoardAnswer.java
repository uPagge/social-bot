package org.sadtech.vkbot.autoresponder.entity;

import org.sadtech.autoresponder.entity.Unit;
import org.sadtech.vkbot.core.keyboard.KeyBoard;

import java.util.List;
import java.util.Set;

public class KeyBoardAnswer extends Unit {

    KeyBoard keyBoard;

    public KeyBoardAnswer() {

    }

    public KeyBoardAnswer(Set<String> keyWords, Integer matchThreshold, Integer priority, Boolean level, List<Unit> nextUnits, KeyBoard keyBoard) {
        super(keyWords, matchThreshold, priority, level, nextUnits);
        this.keyBoard = keyBoard;
    }

    public KeyBoard getKeyBoard() {
        return keyBoard;
    }

    public void setKeyBoard(KeyBoard keyBoard) {
        this.keyBoard = keyBoard;
    }
}

package org.sadtech.vkbot.autoresponder.entity;

import org.sadtech.autoresponder.entity.Unit;
import org.sadtech.vkbot.core.keyboard.KeyBoard;

import java.util.List;
import java.util.Set;

public class TextAnswer extends KeyBoardAnswer {

    private String answer;

    public TextAnswer() {

    }

    public TextAnswer(Set<String> keyWords, Integer matchThreshold, Integer priority, Boolean level, List<Unit> nextUnits, KeyBoard keyBoard, String answer) {
        super(keyWords, matchThreshold, priority, level, nextUnits, keyBoard);
        this.answer = answer;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}

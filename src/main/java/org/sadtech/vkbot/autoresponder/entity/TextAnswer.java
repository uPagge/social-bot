package org.sadtech.vkbot.autoresponder.entity;

import org.sadtech.autoresponder.entity.Unit;
import org.sadtech.vkbot.autoresponder.action.Insert;

import java.util.List;
import java.util.Set;

public class TextAnswer extends Unit {

    private BoxAnswer boxAnswer;
    private Insert insert;

    public TextAnswer() {
        boxAnswer = new BoxAnswer();
    }

    public TextAnswer(Set<String> keyWords, Integer matchThreshold, Integer priority, Boolean level, List<Unit> nextUnits, BoxAnswer boxAnswer) {
        super(keyWords, matchThreshold, priority, level, nextUnits);
        this.boxAnswer = boxAnswer;
    }


    public BoxAnswer getBoxAnswer() {
        return boxAnswer;
    }

    public void setBoxAnswer(BoxAnswer boxAnswer) {
        this.boxAnswer = boxAnswer;
    }

    public Insert getInsert() {
        return insert;
    }

    public void setInsert(Insert insert) {
        this.insert = insert;
    }
}

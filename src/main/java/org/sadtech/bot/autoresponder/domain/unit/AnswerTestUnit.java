package org.sadtech.bot.autoresponder.domain.unit;

import org.sadtech.bot.autoresponder.domain.usercode.TestInsert;
import org.sadtech.bot.autoresponder.service.action.AnswerTestUnitAction;

public class AnswerTestUnit extends MainUnit {

    private MainUnit yes;
    private MainUnit no;
    private MainUnit dataNull;
    private TempSave tempSave;
    private TestInsert testInsert;

    public AnswerTestUnit() {
        typeUnit = TypeUnit.YES_OR_NO;
    }

    public MainUnit getYes() {
        return yes;
    }

    public void setYes(MainUnit yes) {
        this.yes = yes;
        super.setKeyWords(AnswerTestUnitAction.WORDS_YES);
    }

    public MainUnit getNo() {
        return no;
    }

    public void setNo(MainUnit no) {
        this.no = no;
        super.setKeyWords(AnswerTestUnitAction.WORDS_NO);
    }

    public TempSave getTempSave() {
        return tempSave;
    }

    public void setTempSave(TempSave tempSave) {
        this.tempSave = tempSave;
    }

    public TestInsert getTestInsert() {
        return testInsert;
    }

    public void setTestInsert(TestInsert testInsert) {
        this.testInsert = testInsert;
    }

    public MainUnit getDataNull() {
        return dataNull;
    }

    public void setDataNull(MainUnit dataNull) {
        this.dataNull = dataNull;
    }
}

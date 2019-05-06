package org.sadtech.bot.autoresponder.service.action;

import javafx.util.Pair;
import org.sadtech.autoresponder.service.UnitPointerService;
import org.sadtech.bot.autoresponder.domain.unit.AnswerTestUnit;
import org.sadtech.bot.autoresponder.domain.unit.AnswerText;
import org.sadtech.bot.autoresponder.domain.unit.MainUnit;
import org.sadtech.bot.autoresponder.domain.unit.TypeUnit;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AnswerTestUnitAction implements ActionUnit<AnswerTestUnit> {

    private final Map<TypeUnit, ActionUnit> actionUnitMap;
    private final UnitPointerService unitPointerService;
    public final static Set<String> WORDS_YES = Stream.of("да", "WORDS_YES", "ага").collect(Collectors.toSet());
    public final static Set<String> WORDS_NO = Stream.of("нет", "WORDS_NO", "неа").collect(Collectors.toSet());

    public AnswerTestUnitAction(Map<TypeUnit, ActionUnit> actionUnitMap, UnitPointerService unitPointerService) {
        this.actionUnitMap = actionUnitMap;
        this.unitPointerService = unitPointerService;
    }

    @Override
    public MainUnit action(AnswerTestUnit unit, String message, Integer userId) {
        if (WORDS_YES.contains(message.toLowerCase())) {
            String save = unit.getTempSave().load(userId);
            return actionUnit(unit.getYes(), save, userId);
        } else if (WORDS_NO.contains(message.toLowerCase())) {
            String save = unit.getTempSave().load(userId);
            return actionUnit(unit.getNo(), save, userId);
        } else {
            Pair<String, String> save = unit.getTestInsert().insert(userId, message);
            if (save.getValue() == null) {
                return actionUnit(unit.getDataNull(), message, userId);
            } else {
                unit.getTempSave().save(userId, save.getValue());
                AnswerText answerText = AnswerText.builder().message(save.getKey()).nextUnit(unit).build();
                return actionUnit(answerText, message, userId);
            }
        }
    }

    private MainUnit actionUnit(MainUnit mainUnit, String text, Integer userId) {
        actionUnitMap.get(mainUnit.getTypeUnit()).action(mainUnit, text, userId);
        unitPointerService.getByEntityId(userId).setUnit(mainUnit);
        return mainUnit;
    }
}

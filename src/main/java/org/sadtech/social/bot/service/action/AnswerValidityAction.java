package org.sadtech.social.bot.service.action;

import org.sadtech.social.bot.domain.unit.AnswerText;
import org.sadtech.social.bot.domain.unit.AnswerValidity;
import org.sadtech.social.bot.domain.unit.MainUnit;
import org.sadtech.social.bot.utils.Pair;
import org.sadtech.social.core.domain.BoxAnswer;
import org.sadtech.social.core.domain.content.Message;
import org.sadtech.social.core.utils.KeyBoards;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Обработчик Unit-а {@link AnswerValidity}.
 *
 * @author upagge [11/07/2019]
 */
public class AnswerValidityAction implements ActionUnit<AnswerValidity, Message> {

    public static final Set<String> WORDS_YES = Collections.unmodifiableSet(Stream.of("да", "ага").collect(Collectors.toSet()));
    public static final Set<String> WORDS_NO = Collections.unmodifiableSet(Stream.of("нет", "неа").collect(Collectors.toSet()));
    public static final Set<String> WORDS_YES_NO = Collections.unmodifiableSet(Stream.of("да", "ага", "нет", "неа").collect(Collectors.toSet()));

    @Override
    public MainUnit action(AnswerValidity unit, Message content) {
        String message = content.getText();
        Long personId = content.getPersonId();
        if (WORDS_YES.contains(message.toLowerCase())) {
            unit.getTempSave().getByKey(personId, "temp").ifPresent(content::setText);
            return unit.getUnitYes();
        } else if (WORDS_NO.contains(message.toLowerCase())) {
            unit.getTempSave().getByKey(personId, "temp").ifPresent(content::setText);
            return unit.getUnitNo();
        } else {
            Pair<String, String> save = unit.getPairInsert().insert(content);
            if (save.getValue() == null) {
                return unit.getUnitNull();
            } else {
                unit.getTempSave().save(personId, "temp", save.getValue());
                BoxAnswer boxAnswer = BoxAnswer.builder()
                        .message(save.getKey())
                        .keyBoard(KeyBoards.keyBoardYesNo())
                        .build();
                AnswerValidity newValidity = unit.toBuilder()
                        .clearKeyWords().keyWords(WORDS_YES_NO)
                        .build();
                return AnswerText.builder().boxAnswer(boxAnswer).nextUnit(newValidity).build();
            }
        }
    }

}

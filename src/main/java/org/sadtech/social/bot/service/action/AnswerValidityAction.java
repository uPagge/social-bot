package org.sadtech.social.bot.service.action;

import org.sadtech.social.bot.domain.Clarification;
import org.sadtech.social.bot.domain.unit.AnswerText;
import org.sadtech.social.bot.domain.unit.AnswerValidity;
import org.sadtech.social.bot.domain.unit.MainUnit;
import org.sadtech.social.core.domain.content.Message;

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
            Clarification clarification = unit.getClarificationQuestion().getClarification(content);
            final String value = clarification.getValue();
            if (value == null) {
                return unit.getUnitNull();
            } else {
                unit.getTempSave().save(personId, "temp", value);
                AnswerValidity newValidity = unit.toBuilder()
                        .clearKeyWords().keyWords(WORDS_YES_NO)
                        .build();
                return AnswerText.builder()
                        .boxAnswer(clarification.getQuestion())
                        .nextUnit(newValidity)
                        .build();
            }
        }
    }

}

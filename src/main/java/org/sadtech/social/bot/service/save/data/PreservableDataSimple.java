package org.sadtech.social.bot.service.save.data;

import javafx.util.Pair;
import org.sadtech.social.core.domain.content.Message;

/**
 * TODO: Добавить описание класса.
 *
 * @author upagge [13/07/2019]
 */
public class PreservableDataSimple implements PreservableData<Pair<String, String>, Message> {

    private final String fieldName;

    public PreservableDataSimple(String fieldName) {
        this.fieldName = fieldName;
    }

    @Override
    public Pair<String, String> getData(Message content) {
        return new Pair<>(fieldName, content.getText());
    }
}

package org.sadtech.social.bot.utils;

import org.sadtech.social.bot.service.save.Preservable;
import org.sadtech.social.bot.service.save.push.Pusher;

/**
 * TODO: Добавить описание класса.
 *
 * @author upagge [14/07/2019]
 */
public class QuestionnaireUtils {

    private final Preservable preservable;
    private Pusher pusher;

    public QuestionnaireUtils(Preservable preservable) {
        this.preservable = preservable;
    }
}

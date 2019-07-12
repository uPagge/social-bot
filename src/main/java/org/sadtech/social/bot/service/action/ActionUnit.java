package org.sadtech.social.bot.service.action;

import org.sadtech.social.bot.domain.unit.MainUnit;
import org.sadtech.social.core.domain.content.Message;

/**
 * Интерфейс для обработки Unit-ов.
 *
 * @author upagge [11/07/2019]
 */
@FunctionalInterface
public interface ActionUnit<M extends MainUnit, C extends Message> {

    /**
     * Метод обработки Unit-а.
     *
     * @param unit    Unit, который необходимо обработать
     * @param content Запрос пользователя
     * @return Новый Unit, который может нуждаться в обработке
     */
    MainUnit action(M unit, C content);

}

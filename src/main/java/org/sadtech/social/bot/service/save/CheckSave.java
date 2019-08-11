package org.sadtech.social.bot.service.save;

import org.sadtech.social.bot.domain.unit.MainUnit;
import org.sadtech.social.core.domain.content.Message;

/**
 * TODO: Добавить описание интерфейса.
 *
 * @author upagge [04/08/2019]
 */
@FunctionalInterface
public interface CheckSave<D extends Message> {

    MainUnit check(D content);

}

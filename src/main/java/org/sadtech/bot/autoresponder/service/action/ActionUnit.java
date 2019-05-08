package org.sadtech.bot.autoresponder.service.action;

import org.sadtech.bot.autoresponder.domain.unit.MainUnit;
import org.sadtech.bot.core.domain.Content;

public interface ActionUnit<M extends MainUnit, C extends Content> {

    MainUnit action(M unit, C content);

}

package org.sadtech.bot.autoresponder.service.action;

import org.sadtech.bot.autoresponder.domain.unit.MainUnit;
import org.sadtech.bot.core.domain.content.Message;

@FunctionalInterface
public interface ActionUnit<M extends MainUnit, C extends Message> {

    MainUnit action(M unit, C content);

}

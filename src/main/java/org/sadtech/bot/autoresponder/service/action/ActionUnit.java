package org.sadtech.bot.autoresponder.service.action;

import org.sadtech.bot.autoresponder.domain.unit.MainUnit;

public interface ActionUnit<T extends MainUnit> {

    MainUnit action(T unit, String message, Integer userId);

}

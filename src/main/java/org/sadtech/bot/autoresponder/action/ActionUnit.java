package org.sadtech.bot.autoresponder.action;

import org.sadtech.bot.autoresponder.domain.unit.MainUnit;

public interface ActionUnit<T extends MainUnit> {

    void action(T unit, String message, Integer idPerson);

}

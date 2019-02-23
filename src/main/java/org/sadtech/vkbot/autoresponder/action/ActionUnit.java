package org.sadtech.vkbot.autoresponder.action;

import org.sadtech.vkbot.autoresponder.entity.unit.MainUnit;

public interface ActionUnit<T extends MainUnit> {

    void action(T unit, String message, Integer idPerson);

}

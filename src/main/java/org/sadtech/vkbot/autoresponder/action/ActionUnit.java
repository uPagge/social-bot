package org.sadtech.vkbot.autoresponder.action;

import org.sadtech.vkbot.autoresponder.entity.unit.MainUnit;

public interface ActionUnit {

    void action(MainUnit unit, String message, Integer idPerson);

}

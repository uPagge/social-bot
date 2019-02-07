package org.sadtech.vkbot.autoresponder.service;

import org.sadtech.autoresponder.entity.Unit;
import org.sadtech.vkbot.autoresponder.action.ActionUnit;

public interface ActionService {

    ActionUnit get(Class<? extends Unit> aClass);

    void put(Class clazz, ActionUnit actionUnit);

}

package org.sadtech.vkbot.autoresponder.repository;

import org.sadtech.autoresponder.entity.Unit;
import org.sadtech.vkbot.autoresponder.action.ActionUnit;

public interface ActionRepository {
    ActionUnit get(Class<? extends Unit> aClass);

    void put(Class clazz, ActionUnit actionUnit);
}

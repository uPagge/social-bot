package org.sadtech.vkbot.autoresponder.repository;

import org.sadtech.autoresponder.entity.Unit;
import org.sadtech.vkbot.autoresponder.action.ActionUnit;

import java.util.HashMap;
import java.util.Map;

public class ActionRepositoryMap implements ActionRepository {

    private Map<Class, ActionUnit> actionUnitHashMap = new HashMap<>();


    @Override
    public ActionUnit get(Class<? extends Unit> aClass) {
        return actionUnitHashMap.get(aClass);
    }

    @Override
    public void put(Class clazz, ActionUnit actionUnit) {
        actionUnitHashMap.put(clazz, actionUnit);
    }
}

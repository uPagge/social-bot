package org.sadtech.vkbot.autoresponder.service;

import org.sadtech.autoresponder.entity.Unit;
import org.sadtech.vkbot.autoresponder.action.ActionUnit;
import org.sadtech.vkbot.autoresponder.repository.ActionRepository;
import org.sadtech.vkbot.autoresponder.repository.ActionRepositoryMap;

public class ActionServiceImpl implements ActionService {

    private ActionRepository actionRepository;

    public ActionServiceImpl() {
        actionRepository = new ActionRepositoryMap();
    }

    public ActionServiceImpl(ActionRepository actionRepository) {
        this.actionRepository = actionRepository;
    }

    public ActionRepository getActionRepository() {
        return actionRepository;
    }

    public void setActionRepository(ActionRepository actionRepository) {
        this.actionRepository = actionRepository;
    }

    @Override
    public ActionUnit get(Class<? extends Unit> aClass) {
        return actionRepository.get(aClass);
    }

    @Override
    public void put(Class clazz, ActionUnit actionUnit) {
        actionRepository.put(clazz, actionUnit);
    }

}

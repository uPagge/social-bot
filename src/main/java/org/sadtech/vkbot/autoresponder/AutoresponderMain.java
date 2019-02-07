package org.sadtech.vkbot.autoresponder;

import org.sadtech.autoresponder.Autoresponder;
import org.sadtech.autoresponder.service.PersonService;
import org.sadtech.autoresponder.service.UnitService;
import org.sadtech.autoresponder.service.impl.PersonServiceImpl;
import org.sadtech.autoresponder.service.impl.UnitServiceImpl;
import org.sadtech.vkbot.autoresponder.action.Action;
import org.sadtech.vkbot.autoresponder.repository.UnitMenuRepository;
import org.sadtech.vkbot.core.service.distribution.impl.EventService;

import java.util.Date;
import java.util.List;

public abstract class AutoresponderMain<T> implements Runnable {

    private EventService<T> eventService;
    protected Action action;
    private UnitService unitService;
    protected Autoresponder autoresponder;

    public AutoresponderMain(EventService<T> eventService, Action action, UnitService unitService) {
        this.unitService = unitService;
        this.eventService = eventService;
        this.action = action;
        PersonService personService = new PersonServiceImpl();
        autoresponder = new Autoresponder(this.unitService, personService);
        action.setPersonService(personService);
    }

    public AutoresponderMain(EventService<T> eventService, Action action) {
        this.unitService = new UnitServiceImpl(new UnitMenuRepository());
        this.eventService = eventService;
        this.action = action;
        PersonService personService = new PersonServiceImpl();
        autoresponder = new Autoresponder(unitService, personService);
        action.setPersonService(personService);
    }

    public Autoresponder getAutoresponder() {
        return autoresponder;
    }

    public UnitService getUnitService() {
        return unitService;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    private void checkNewMessages() {
        Long oldData = new Date().getTime() / 1000 - 1;
        Long newData;
        while (true) {
            newData = new Date().getTime() / 1000 - 1;
            if (oldData < newData) {
                List<T> mailList = eventService.getFirstMailByTime(Integer.parseInt(oldData.toString()), Integer.parseInt(newData.toString()));
                if (mailList.size() > 0) {
                    sendReply(mailList);
                }
            }
            oldData = new Long(newData.toString());
        }
    }

    protected abstract void sendReply(List<T> mailList);

    @Override
    public void run() {
        checkNewMessages();
    }
}

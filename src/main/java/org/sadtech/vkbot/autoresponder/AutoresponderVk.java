package org.sadtech.vkbot.autoresponder;

import org.sadtech.autoresponder.Autoresponder;
import org.sadtech.autoresponder.entity.Unit;
import org.sadtech.autoresponder.repository.UnitRepository;
import org.sadtech.autoresponder.repository.impl.PersonRepositoryMap;
import org.sadtech.autoresponder.service.UnitService;
import org.sadtech.autoresponder.service.impl.PersonServiceImpl;
import org.sadtech.autoresponder.service.impl.UnitServiceImpl;
import org.sadtech.vkbot.core.repository.unit.TextAnswerRepository;
import org.sadtech.vkbot.core.VkConnect;
import org.sadtech.vkbot.core.entity.Mail;
import org.sadtech.vkbot.core.handlers.Handled;
import org.sadtech.vkbot.core.handlers.ProcessableEvent;
import org.sadtech.vkbot.core.handlers.impl.DispatcherHandlerVk;
import org.sadtech.vkbot.core.handlers.impl.MailHandlerVk;
import org.sadtech.vkbot.core.listener.EventListenable;
import org.sadtech.vkbot.core.listener.impl.EventListenerVk;
import org.sadtech.vkbot.core.repository.impl.EventRepositoryQueue;
import org.sadtech.vkbot.core.sender.MailSanderVk;
import org.sadtech.vkbot.core.service.impl.EventServiceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AutoresponderVk {

    private VkConnect vkConnect;
    private Autoresponder autoresponder;
    private Handled handled;
    private EventListenable eventListenable;
    private ProcessableEvent mailHandler;
    private MailSanderVk mailSanderVk;
    private ActionUnit actionUnit;

    public UnitService getUnitService() {
        return unitService;
    }

    private UnitService unitService;

    public AutoresponderVk(VkConnect vkConnect) {
        this.vkConnect = vkConnect;
        mailSanderVk = new MailSanderVk(vkConnect);
        ArrayList<UnitRepository> unitRepositories = new ArrayList<>();
        unitRepositories.add(new TextAnswerRepository());
        unitService = new UnitServiceImpl(unitRepositories);
        autoresponder = new Autoresponder(unitService, new PersonServiceImpl(new PersonRepositoryMap()));
        handled = new DispatcherHandlerVk(new EventServiceImpl(new EventRepositoryQueue()));
        mailHandler = new MailHandlerVk(handled);
        eventListenable = new EventListenerVk(vkConnect, handled.getResponsibleService().getEventRepository());
    }

    public void start() {
        init();
        Long oldData = new Date().getTime() / 1000;
        Long data;
        List<Mail> mailList;
        actionUnit = new ActionUnit(mailSanderVk);
        while (true) {
            data = new Date().getTime() / 1000;
            mailList = mailHandler.getServiceEventData().getFirstMailByTime(new Integer(String.valueOf(oldData)), new Integer(String.valueOf(data)));
            if (mailList.size() > 0) {
                for (Mail mail : mailList) {
                    mailSanderVk.setPerson(mail.getPerson());
                    Unit unitAnswer = autoresponder.answer(mail.getPerson().getId(), mail.getBody());
                    if (unitAnswer != null) {
                        actionUnit.setUnit(unitAnswer);
                        actionUnit.action();
                    } else {
                        mailSanderVk.sendText("К сожалению, я еще не знаю что вам ответить");
                    }
                }
            }
            oldData = Long.valueOf(data.toString());
            sleep(7000);
        }

    }

    private void sleep(Integer sleepTime) {
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void init() {
        new Thread(eventListenable).start();
        new Thread(handled).start();
    }

}

package org.sadtech.vkbot.autoresponder;

import org.sadtech.autoresponder.Autoresponder;
import org.sadtech.autoresponder.entity.Unit;
import org.sadtech.autoresponder.repository.UnitRepository;
import org.sadtech.autoresponder.repository.impl.PersonRepositoryMap;
import org.sadtech.autoresponder.service.UnitService;
import org.sadtech.autoresponder.service.impl.PersonServiceImpl;
import org.sadtech.autoresponder.service.impl.UnitServiceImpl;
import org.sadtech.vkbot.autoresponder.action.GeneralActionUnit;
import org.sadtech.vkbot.autoresponder.action.TextAnswerAction;
import org.sadtech.vkbot.autoresponder.action.TextAnswerAndSaveAction;
import org.sadtech.vkbot.autoresponder.repository.TextAnswerRepository;
import org.sadtech.vkbot.core.VkConnect;
import org.sadtech.vkbot.core.entity.Mail;
import org.sadtech.vkbot.core.handlers.Handled;
import org.sadtech.vkbot.core.handlers.ProcessableEvent;
import org.sadtech.vkbot.core.handlers.impl.DispatcherHandlerVk;
import org.sadtech.vkbot.core.handlers.impl.MailHandlerVk;
import org.sadtech.vkbot.core.listener.EventListener;
import org.sadtech.vkbot.core.listener.EventListenerVk;
import org.sadtech.vkbot.core.repository.impl.EventRepositoryQueue;
import org.sadtech.vkbot.core.sender.MailSanderVk;
import org.sadtech.vkbot.core.sender.MailSend;
import org.sadtech.vkbot.core.service.impl.EventServiceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AutoresponderVk {

    private Autoresponder autoresponder;
    private Handled handled;
    private EventListener eventListener;
    private ProcessableEvent mailHandler;
    private UnitService unitService;
    private VkConnect vkConnect;

    public UnitService getUnitService() {
        return unitService;
    }

    public AutoresponderVk(VkConnect vkConnect) {
        this.vkConnect = vkConnect;
        ArrayList<UnitRepository> unitRepositories = new ArrayList<>();
        unitRepositories.add(new TextAnswerRepository());
        unitService = new UnitServiceImpl(unitRepositories);
        autoresponder = new Autoresponder(unitService, new PersonServiceImpl(new PersonRepositoryMap()));
        handled = new DispatcherHandlerVk(new EventServiceImpl(new EventRepositoryQueue()));
        mailHandler = new MailHandlerVk(handled);
        eventListener = new EventListenerVk(vkConnect, handled.getResponsibleService().getEventRepository());
    }

    public void start() {
        init();
        checkNewMessages();
    }

    private void checkNewMessages() {
        Long oldData = new Date().getTime() / 1000 - 1;
        Long newData;
        while (true) {
            newData = new Date().getTime() / 1000 - 1;
            if (oldData < newData) {
                List<Mail> mailList = mailHandler.getServiceEventData().getFirstMailByTime(Integer.parseInt(oldData.toString()), Integer.parseInt(newData.toString()));
                if (mailList.size()>0) {
                    sendReply(mailList);
                }
            }
            oldData = new Long(newData.toString());
        }
    }

    private void sendReply(List<Mail> mailList) {
        MailSanderVk mailSanderVk = new MailSanderVk(vkConnect);
        GeneralActionUnit generalActionUnit = new GeneralActionUnit(mailSanderVk);
        new TextAnswerAction(generalActionUnit);
        new TextAnswerAndSaveAction(generalActionUnit);
        for (Mail mail : mailList) {
            Unit unitAnswer = autoresponder.answer(mail.getPerson().getId(), mail.getBody());
            if (unitAnswer != null) {
                generalActionUnit.action(unitAnswer, mail);
            } else {
                MailSend mailSend = new MailSend();
                mailSend.setIdRecipient(mail.getPerson().getId());
                mailSend.setMessage("К сожалению, я еще не знаю что вам ответить");
                mailSanderVk.send(mailSend);
            }
        }
    }

    private void init() {
        new Thread(eventListener).start();
        new Thread(handled).start();
    }

}

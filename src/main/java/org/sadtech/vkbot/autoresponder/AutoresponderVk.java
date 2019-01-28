package org.sadtech.vkbot.autoresponder;

import org.sadtech.autoresponder.Autoresponder;
import org.sadtech.autoresponder.entity.Unit;
import org.sadtech.autoresponder.repository.impl.PersonRepositoryMap;
import org.sadtech.autoresponder.service.UnitService;
import org.sadtech.autoresponder.service.impl.PersonServiceImpl;
import org.sadtech.autoresponder.service.impl.UnitServiceImpl;
import org.sadtech.vkbot.autoresponder.action.GeneralActionUnit;
import org.sadtech.vkbot.autoresponder.action.TextAnswerAction;
import org.sadtech.vkbot.autoresponder.action.TextAnswerAndSaveAction;
import org.sadtech.vkbot.autoresponder.repository.UnitMenuRepository;
import org.sadtech.vkbot.core.VkConnect;
import org.sadtech.vkbot.core.entity.Mail;
import org.sadtech.vkbot.core.sender.MailSanderVk;
import org.sadtech.vkbot.core.sender.MailSend;
import org.sadtech.vkbot.core.service.handlers.MailService;

import java.util.Date;
import java.util.List;

public class AutoresponderVk {

    private Autoresponder autoresponder;
    private UnitService unitService;
    private VkConnect vkConnect;
    private MailService mailService;

    public AutoresponderVk(VkConnect vkConnect) {
        this.vkConnect = vkConnect;
        unitService = new UnitServiceImpl(new UnitMenuRepository());
        autoresponder = new Autoresponder(unitService, new PersonServiceImpl(new PersonRepositoryMap()));
    }

    public AutoresponderVk(VkConnect vkConnect, MailService mailService) {
        this.vkConnect = vkConnect;
        unitService = new UnitServiceImpl(new UnitMenuRepository());
        autoresponder = new Autoresponder(unitService, new PersonServiceImpl(new PersonRepositoryMap()));
        this.mailService = mailService;
    }

    public MailService getMailService() {
        return mailService;
    }

    public void setMailService(MailService mailService) {
        this.mailService = mailService;
    }

    public UnitService getUnitService() {
        return unitService;
    }

    public void start() {
        checkNewMessages();
    }

    private void checkNewMessages() {
        Long oldData = new Date().getTime() / 1000 - 1;
        Long newData;
        while (true) {
            newData = new Date().getTime() / 1000 - 1;
            if (oldData < newData) {
                List<Mail> mailList = mailService.getFirstMailByTime(Integer.parseInt(oldData.toString()), Integer.parseInt(newData.toString()));
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

}

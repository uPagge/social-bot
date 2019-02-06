package org.sadtech.vkbot.autoresponder;

import org.sadtech.autoresponder.Autoresponder;
import org.sadtech.autoresponder.service.PersonService;
import org.sadtech.autoresponder.service.UnitService;
import org.sadtech.autoresponder.service.impl.UnitServiceImpl;
import org.sadtech.vkbot.autoresponder.action.Action;
import org.sadtech.vkbot.autoresponder.entity.MainUnit;
import org.sadtech.vkbot.autoresponder.repository.UnitMenuRepository;
import org.sadtech.vkbot.core.VkConnect;
import org.sadtech.vkbot.core.entity.Mail;
import org.sadtech.vkbot.core.entity.MailSend;
import org.sadtech.vkbot.core.sender.MailSenderVk;
import org.sadtech.vkbot.core.service.handlers.MailService;

import java.util.Date;
import java.util.List;

public class AutoresponderVk implements Runnable {

    private VkConnect vkConnect;
    private Autoresponder autoresponder;
    private UnitService unitService;
    private MailService mailService;
    private Action generalAction;
    private PersonService personService;

    public AutoresponderVk(VkConnect vkConnect, UnitService unitService, MailService mailService, Action generalAction, PersonService personService) {
        this.unitService = unitService;
        this.vkConnect = vkConnect;
        this.mailService = mailService;
        this.generalAction = generalAction;
        this.personService = personService;
        autoresponder = new Autoresponder(this.unitService, personService);
        generalAction.setPersonService(personService);
    }

    public AutoresponderVk(VkConnect vkConnect, MailService mailService, Action generalAction, PersonService personService) {
        this.vkConnect = vkConnect;
        unitService = new UnitServiceImpl(new UnitMenuRepository());
        autoresponder = new Autoresponder(unitService, personService);
        this.generalAction = generalAction;
        this.mailService = mailService;
        this.personService = personService;
        generalAction.setPersonService(personService);
    }

    public Autoresponder getAutoresponder() {
        return autoresponder;
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

    public Action getGeneralAction() {
        return generalAction;
    }

    public void setGeneralAction(Action generalAction) {
        this.generalAction = generalAction;
    }

    private void checkNewMessages() {
        Long oldData = new Date().getTime() / 1000 - 1;
        Long newData;
        while (true) {
            newData = new Date().getTime() / 1000 - 1;
            if (oldData < newData) {
                List<Mail> mailList = mailService.getFirstMailByTime(Integer.parseInt(oldData.toString()), Integer.parseInt(newData.toString()));
                if (mailList.size() > 0) {
                    sendReply(mailList);
                }
            }
            oldData = new Long(newData.toString());
        }
    }

    private void sendReply(List<Mail> mailList) {
        MailSenderVk mailSenderVk = new MailSenderVk(vkConnect);
        for (Mail mail : mailList) {
            MainUnit unitAnswer = (MainUnit) autoresponder.answer(mail.getPerson().getId(), mail.getBody());
            if (unitAnswer != null) {
                generalAction.action(unitAnswer, mail);
            } else {
                MailSend mailSend = new MailSend();
                mailSend.setMessage("К сожалению, я еще не знаю что вам ответить");
                mailSenderVk.send(mailSend, mail.getPeerId(), mail.getPerson().getId());
            }
            if (unitAnswer.getNextUnits() != null) {
                unitAnswer.getNextUnits().forEach(nextUnit -> {
                    if (((MainUnit) nextUnit).getHiddenTrigger()) {
                        generalAction.action((MainUnit) nextUnit, mail);
                        personService.getPersonById(mail.getPerson().getId()).setUnit(nextUnit);
                    }
                });
            }
        }
    }

    @Override
    public void run() {
        checkNewMessages();
    }
}

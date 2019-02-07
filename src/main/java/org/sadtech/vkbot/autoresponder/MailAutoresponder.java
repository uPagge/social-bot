package org.sadtech.vkbot.autoresponder;

import org.sadtech.autoresponder.service.UnitService;
import org.sadtech.vkbot.autoresponder.action.Action;
import org.sadtech.vkbot.autoresponder.entity.MainUnit;
import org.sadtech.vkbot.autoresponder.entity.UnitActivStatus;
import org.sadtech.vkbot.core.VkConnect;
import org.sadtech.vkbot.core.entity.Mail;
import org.sadtech.vkbot.core.entity.MailSend;
import org.sadtech.vkbot.core.sender.MailSenderVk;
import org.sadtech.vkbot.core.service.distribution.impl.EventService;

import java.util.List;

public class MailAutoresponder extends AutoresponderMain<Mail> {

    private VkConnect vkConnect;
    private MailSenderVk mailSenderVk;

    public MailAutoresponder(EventService<Mail> eventService, Action action, UnitService unitService, VkConnect vkConnect) {
        super(eventService, action, unitService);
        this.vkConnect = vkConnect;
        mailSenderVk = new MailSenderVk(vkConnect);
    }

    public MailAutoresponder(EventService<Mail> eventService, Action action, VkConnect vkConnect) {
        super(eventService, action);
        this.vkConnect = vkConnect;
        mailSenderVk = new MailSenderVk(vkConnect);
    }

    @Override
    protected void sendReply(List<Mail> mailList) {
        for (Mail mail : mailList) {

            MainUnit unitAnswer = (MainUnit) autoresponder.answer(mail.getPerson().getId(), mail.getBody());

            if (unitAnswer != null) {
                if (unitAnswer.getUnitActivStatus().equals(UnitActivStatus.DEFAULT) || unitAnswer.getUnitActivStatus().equals(UnitActivStatus.BEFORE)) {
                    action.action(unitAnswer, mail);
                }
            } else {
                MailSend mailSend = new MailSend();
                mailSend.setMessage("К сожалению, я еще не знаю что вам ответить");
                mailSenderVk.send(mailSend, mail.getPeerId(), mail.getPerson().getId());
            }

            //super.test(unitAnswer, mail.getPerson().getId());
            if (unitAnswer.getNextUnits() != null) {
                unitAnswer.getNextUnits().stream().filter(Unit -> Unit instanceof MainUnit).map(unit -> (MainUnit) unit).forEach(nextUnit -> {
                    if (nextUnit.getUnitActivStatus().equals(UnitActivStatus.AFTER)) {
                        action.action(nextUnit, mail);
                        autoresponder.getPersonService().getPersonById(mail.getPerson().getId()).setUnit(nextUnit);
                    }
                });
            }
        }
    }

}

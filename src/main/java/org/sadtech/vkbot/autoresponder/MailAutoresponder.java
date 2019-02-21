package org.sadtech.vkbot.autoresponder;

import org.sadtech.autoresponder.service.UnitService;
import org.sadtech.vkbot.autoresponder.action.Action;
import org.sadtech.vkbot.autoresponder.entity.unit.MainUnit;
import org.sadtech.vkbot.autoresponder.entity.unit.UnitActiveStatus;
import org.sadtech.vkbot.core.VkConnect;
import org.sadtech.vkbot.core.entity.Mail;
import org.sadtech.vkbot.core.sender.Sent;
import org.sadtech.vkbot.core.service.distribution.impl.EventService;

import java.util.List;

public class MailAutoresponder extends AutoresponderMain<Mail> {

    public MailAutoresponder(Sent sent, EventService<Mail> eventService, Action action, UnitService unitService, VkConnect vkConnect) {
        super(sent, eventService, action, unitService);
    }

    public MailAutoresponder(Sent sent, EventService<Mail> eventService, Action action, VkConnect vkConnect) {
        super(sent, eventService, action);
    }

    @Override
    protected void sendReply(List<Mail> mailList) {
        for (Mail mail : mailList) {
            MainUnit unitAnswer = (MainUnit) autoresponder.answer(mail.getPerson().getId(), mail.getBody());
            if (unitAnswer != null) {
                if (unitAnswer.getUnitActiveStatus().equals(UnitActiveStatus.DEFAULT)) {
                    action.action(unitAnswer, mail.getBody(), mail.getPerson().getId());
                }
                activeUnitAfter(unitAnswer, mail);
            } else {
                sent.send(mail.getPeerId(), "К сожалению, я еще не знаю что вам ответить");
            }
        }
    }

    private void activeUnitAfter(MainUnit mainUnit, Mail mail) {
        if (mainUnit.getNextUnits() != null) {
            mainUnit.getNextUnits().stream().filter(Unit -> Unit instanceof MainUnit).map(unit -> (MainUnit) unit).forEach(nextUnit -> {
                if (nextUnit.getUnitActiveStatus().equals(UnitActiveStatus.AFTER)) {
                    action.action(nextUnit, mail.getBody(), mail.getPerson().getId());
                    autoresponder.getPersonService().getPersonById(mail.getPerson().getId()).setUnit(nextUnit);
                }
            });
        }
    }

}

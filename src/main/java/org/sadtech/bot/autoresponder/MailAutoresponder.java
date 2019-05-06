package org.sadtech.bot.autoresponder;

import org.sadtech.bot.autoresponder.domain.unit.MainUnit;
import org.sadtech.bot.autoresponder.domain.unit.UnitActiveStatus;
import org.sadtech.bot.core.domain.Mail;
import org.sadtech.bot.core.sender.Sent;
import org.sadtech.bot.core.service.EventService;

import java.util.List;

public class MailAutoresponder extends GeneralAutoresponder<Mail> {

    public MailAutoresponder(Sent sent, EventService<Mail> eventService) {
        super(sent, eventService);
    }

    @Override
    public void sendReply(List<Mail> mailList) {
        for (Mail mail : mailList) {
            MainUnit unitAnswer = (MainUnit) autoresponder.answer(mail.getPeerId(), mail.getBody());
            if (unitAnswer != null) {
                if (unitAnswer.getUnitActiveStatus().equals(UnitActiveStatus.DEFAULT)) {
                    unitAnswer = actionUnitMap.get(unitAnswer.getTypeUnit()).action(unitAnswer, mail.getBody(), mail.getPeerId());
                    autoresponder.getUnitPointerService().getByEntityId(mail.getPeerId()).setUnit(unitAnswer);
                }
                activeUnitAfter(unitAnswer, mail.getBody(), mail.getPeerId());
            } else {
                sent.send(mail.getPeerId(), "К сожалению, я еще не знаю что вам ответить");
            }
        }
    }

}

package org.sadtech.vkbot.autoresponder;

import org.sadtech.vkbot.autoresponder.domain.unit.MainUnit;
import org.sadtech.vkbot.autoresponder.domain.unit.UnitActiveStatus;
import org.sadtech.vkbot.core.domain.Mail;
import org.sadtech.vkbot.core.sender.Sent;
import org.sadtech.vkbot.core.service.distribution.impl.EventService;

import java.util.List;

public class MailAutoresponder extends GeneralAutoresponder<Mail> {

    public MailAutoresponder(Sent sent, EventService<Mail> eventService) {
        super(sent, eventService);
    }

    @Override
    public void sendReply(List<Mail> mailList) {
        for (Mail mail : mailList) {
            MainUnit unitAnswer = (MainUnit) autoresponder.answer(mail.getPerson().getId(), mail.getBody());
            if (unitAnswer != null) {
                if (unitAnswer.getUnitActiveStatus().equals(UnitActiveStatus.DEFAULT)) {
                    actionUnitMap.get(unitAnswer.getTypeUnit()).action(unitAnswer, mail.getBody(), mail.getPerson().getId());
                }
                activeUnitAfter(unitAnswer, mail);
            } else {
                sent.send(mail.getPeerId(), "К сожалению, я еще не знаю что вам ответить");
            }
        }
    }

}

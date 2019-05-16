package org.sadtech.bot.autoresponder;

import org.sadtech.autoresponder.entity.Unit;
import org.sadtech.bot.autoresponder.domain.unit.MainUnit;
import org.sadtech.bot.autoresponder.domain.unit.UnitActiveStatus;
import org.sadtech.bot.core.domain.Mail;
import org.sadtech.bot.core.sender.Sent;
import org.sadtech.bot.core.service.EventService;

import java.util.Set;

public class MailAutoresponder extends GeneralAutoresponder<Mail> {

    public MailAutoresponder(Set<Unit> menuUnit, Sent sent, EventService<Mail> eventService) {
        super(menuUnit, sent, eventService);
    }

    @Override
    public MainUnit processing(Mail mail) {
        MainUnit unitAnswer = (MainUnit) autoresponder.answer(mail.getPersonId(), mail.getMessage());
        if (UnitActiveStatus.DEFAULT.equals(unitAnswer.getActiveStatus())) {
            unitAnswer = actionUnitMap.get(unitAnswer.getTypeUnit()).action(unitAnswer, mail);
        }
        return unitAnswer;
    }
}


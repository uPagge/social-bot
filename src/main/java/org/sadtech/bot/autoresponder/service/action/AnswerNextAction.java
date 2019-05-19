package org.sadtech.bot.autoresponder.service.action;

import org.sadtech.autoresponder.Autoresponder;
import org.sadtech.bot.autoresponder.domain.unit.AnswerNext;
import org.sadtech.bot.autoresponder.domain.unit.MainUnit;
import org.sadtech.bot.core.domain.Mail;

public class AnswerNextAction implements ActionUnit<AnswerNext, Mail> {

    private final Autoresponder autoresponder;

    public AnswerNextAction(Autoresponder autoresponder) {
        this.autoresponder = autoresponder;
    }

    @Override
    public MainUnit action(AnswerNext unit, Mail mail) {
        return (MainUnit) autoresponder.answer(mail.getPersonId(), mail.getMessage());
    }

}

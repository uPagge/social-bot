package org.sadtech.bot.autoresponder.service.action;

import org.sadtech.bot.autoresponder.domain.unit.AnswerProcessing;
import org.sadtech.bot.autoresponder.domain.unit.MainUnit;
import org.sadtech.bot.core.domain.Mail;
import org.sadtech.bot.core.sender.Sent;

public class AnswerProcessingAction implements ActionUnit<AnswerProcessing, Mail> {

    private final Sent sent;

    public AnswerProcessingAction(Sent sent) {
        this.sent = sent;
    }

    @Override
    public MainUnit action(AnswerProcessing answerProcessing, Mail mail) {
        sent.send(mail.getPersonId(), answerProcessing.getProcessingData().processing(mail.getPersonId(), mail.getMessage()));
        return answerProcessing;
    }
}

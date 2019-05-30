package org.sadtech.bot.autoresponder.service.action;

import org.sadtech.bot.autoresponder.domain.unit.AnswerProcessing;
import org.sadtech.bot.autoresponder.domain.unit.MainUnit;
import org.sadtech.bot.core.domain.BoxAnswer;
import org.sadtech.bot.core.domain.content.Content;
import org.sadtech.bot.core.service.sender.SenderBox;
import org.sadtech.bot.core.service.sender.Sent;

public class AnswerProcessingAction implements ActionUnit<AnswerProcessing, Content> {

    private final Sent sent;

    public AnswerProcessingAction(Sent sent) {
        this.sent = sent;
    }

    @Override
    public MainUnit action(AnswerProcessing answerProcessing, Content content) {
        BoxAnswer boxAnswer = answerProcessing.getProcessingData().processing(content);
        SenderBox.sent((answerProcessing.getSent() != null) ? answerProcessing.getSent() : sent, content, boxAnswer);
        return answerProcessing;
    }

}

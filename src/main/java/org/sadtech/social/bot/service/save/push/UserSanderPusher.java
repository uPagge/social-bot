package org.sadtech.social.bot.service.save.push;

import javafx.util.Pair;
import org.sadtech.social.core.domain.BoxAnswer;
import org.sadtech.social.core.service.sender.Sent;

import java.util.List;

public class UserSanderPusher implements Pusher<Pair<String, String>> {

    private final Integer personId;
    private final String nameForm;
    private final Sent sent;

    public UserSanderPusher(Integer personId, String nameForm, Sent sent) {
        this.personId = personId;
        this.nameForm = nameForm;
        this.sent = sent;
    }

    @Override
    public void push(List<Pair<String, String>> saveElement) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("========= ").append(nameForm).append(" =========\n");
        saveElement.forEach(pair -> stringBuilder
                .append(pair.getKey()).append(": ").append(pair.getValue()).append("\n"));
        stringBuilder.append("====================");
        sent.send(this.personId, BoxAnswer.builder().message(stringBuilder.toString()).build());
    }
}

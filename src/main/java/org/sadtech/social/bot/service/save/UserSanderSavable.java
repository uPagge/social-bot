package org.sadtech.social.bot.service.save;

import javafx.util.Pair;
import org.sadtech.social.core.domain.BoxAnswer;

public class UserSanderSavable extends LocalListSavable<Pair> {

    private final Integer personId;

    public UserSanderSavable(Integer personId) {
        this.personId = personId;
    }

    @Override
    public void push(Integer personId) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("========= ").append(nameForm).append(" =========\n");
        download(personId)
                .forEach(pair -> stringBuilder
                        .append(pair.getKey()).append(": ").append(pair.getValue()).append("\n"));
        stringBuilder.append("====================");
        saveMap.remove(personId);
        sent.send(this.personId, BoxAnswer.builder().message(stringBuilder.toString()).build());
    }

}

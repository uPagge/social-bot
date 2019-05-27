package org.sadtech.bot.autoresponder.service.save;

import javafx.util.Pair;

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
        sent.send(this.personId, stringBuilder.toString());
    }

}

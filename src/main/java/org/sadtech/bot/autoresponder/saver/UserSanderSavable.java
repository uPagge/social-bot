package org.sadtech.bot.autoresponder.saver;

import org.sadtech.bot.core.sender.Sent;

public class UserSanderSavable extends LocalSavable {

    private final Sent sent;
    private final Integer personId;

    public UserSanderSavable(Sent sent, Integer personId) {
        this.personId = personId;
        this.sent = sent;
    }

    @Override
    public void push(Integer userId) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("========= ").append(nameForm).append(" =========\n");
        for (String s : map.get(userId).keySet()) {
            stringBuilder.append(s).append(": ").append(map.get(userId).get(s)).append("\n");
        }
        stringBuilder.append("====================");
        map.remove(userId);
        sent.send(personId, stringBuilder.toString());
    }

}

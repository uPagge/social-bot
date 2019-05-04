package org.sadtech.bot.autoresponder.saver;

import org.sadtech.bot.core.sender.Sent;

public class UserSanderSavable extends LocalSavable {

    private Sent sent;
    private Integer idUser;

    public UserSanderSavable(Sent sent, Integer idUser) {
        this.idUser = idUser;
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
        sent.send(idUser, stringBuilder.toString());
    }

}

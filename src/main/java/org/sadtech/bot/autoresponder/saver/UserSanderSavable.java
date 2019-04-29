package org.sadtech.bot.autoresponder.saver;

import org.sadtech.bot.core.sender.Sent;

import java.util.HashMap;
import java.util.Map;

public class UserSanderSavable implements Savable {

    private Sent sent;
    private Integer idUser;
    private String nameForm;
    private Map<Integer, Map<String, String>> map = new HashMap<>();

    public UserSanderSavable(Sent sent, Integer idUser) {
        this.idUser = idUser;
        this.sent = sent;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    @Override
    public void init(Integer userId) {
        map.put(userId, new HashMap<>());
    }

    @Override
    public void save(Integer userId, String key, String value) {
        map.get(userId).put(key, value);
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

    public String getNameForm() {
        return nameForm;
    }

    public void setNameForm(String nameForm) {
        this.nameForm = nameForm;
    }

}

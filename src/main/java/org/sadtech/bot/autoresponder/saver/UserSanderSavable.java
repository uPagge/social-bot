package org.sadtech.bot.autoresponder.saver;

public class UserSanderSavable extends LocalSavable {

    private final Integer personId;

    public UserSanderSavable(Integer personId) {
        this.personId = personId;
    }

    @Override
    public void push(Integer personId) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("========= ").append(nameForm).append(" =========\n");
        for (String s : map.get(personId).keySet()) {
            stringBuilder.append(s).append(": ").append(map.get(personId).get(s)).append("\n");
        }
        stringBuilder.append("====================");
        map.remove(personId);
        sent.send(this.personId, stringBuilder.toString());
    }

}

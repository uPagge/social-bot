package org.sadtech.vkbot.autoresponder.saver;

import org.sadtech.vkbot.core.VkConnect;
import org.sadtech.vkbot.core.sender.MailSanderVk;
import org.sadtech.vkbot.core.entity.MailSend;

import java.util.HashMap;
import java.util.Map;

public class UserSanderSaver implements Saver {

    private Integer idUser;
    private VkConnect vkConnect;
    private String nameForm;
    private Map<Integer, Map<String, String>> map = new HashMap<>();

    public UserSanderSaver(VkConnect vkConnect) {
        this.vkConnect = vkConnect;
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
        MailSanderVk mailSandler = new MailSanderVk(vkConnect);
        MailSend mailSend = new MailSend();
        mailSend.setIdRecipient(idUser);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Название формы: ").append(nameForm).append("\nОтправитель: https://vk.com/id").append(userId).append("\n");
        for (String s : map.get(userId).keySet()) {
            stringBuilder.append(s).append(": ").append(map.get(userId).get(s)).append("\n");
        }
        map.remove(userId);
        mailSend.setMessage(stringBuilder.toString());
        mailSandler.send(mailSend);
    }

    public String getNameForm() {
        return nameForm;
    }

    public void setNameForm(String nameForm) {
        this.nameForm = nameForm;
    }
}

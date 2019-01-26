package org.sadtech.vkbot.autoresponder.saver;

import org.sadtech.vkbot.core.VkConnect;
import org.sadtech.vkbot.core.sender.MailSanderVk;

import java.util.HashMap;
import java.util.Map;

public class UserSanderSaver implements Saver {

    private Integer idUser;
    private VkConnect vkConnect;
    private Map<String, String> map;

    public UserSanderSaver(VkConnect vkConnect) {
        this.vkConnect = vkConnect;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    @Override
    public void init() {
        map = new HashMap<>();
    }

    @Override
    public void save(String key, String value) {
        map.put(key, value);
    }

    @Override
    public void push() {
        MailSanderVk mailSandler = new MailSanderVk(vkConnect);
        mailSandler.setIdRecipient(idUser);
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : map.keySet()) {
            stringBuilder.append(s);
            stringBuilder.append(": ");
            stringBuilder.append(map.get(s));
            stringBuilder.append("\n");
        }
        mailSandler.send(stringBuilder.toString());
    }
}

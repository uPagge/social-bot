package org.sadtech.vkbot.autoresponder.saver;

import com.vk.api.sdk.objects.users.UserMin;
import org.sadtech.vkbot.core.VkApi;
import org.sadtech.vkbot.core.VkConnect;
import org.sadtech.vkbot.core.sender.MailSenderVk;
import org.sadtech.vkbot.core.entity.MailSend;

import java.util.HashMap;
import java.util.Map;

public class UserSanderSavable implements Savable {

    private Integer idUser;
    private VkConnect vkConnect;
    private String nameForm;
    private Map<Integer, Map<String, String>> map = new HashMap<>();
    private VkApi vkApi;

    public UserSanderSavable(VkConnect vkConnect) {
        this.vkConnect = vkConnect;
        vkApi = new VkApi(vkConnect);
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
        MailSenderVk mailSandler = new MailSenderVk(vkConnect);
        MailSend mailSend = new MailSend();

        StringBuilder stringBuilder = new StringBuilder();
        UserMin userMin = vkApi.getUserMini(userId);
        stringBuilder.append("========= ").append(nameForm).append(" =========\nОтправитель: ").append(userMin.getFirstName()).append(" ").append(userMin.getLastName()).append("\nСсылка: https://vk.com/id").append(userMin.getId()).append("\n--- --- ---\n");
        for (String s : map.get(userId).keySet()) {
            stringBuilder.append(s).append(": ").append(map.get(userId).get(s)).append("\n");
        }
        stringBuilder.append("====================");
        map.remove(userId);
        mailSend.setMessage(stringBuilder.toString());
        mailSandler.send(mailSend, idUser, idUser);
    }

    public String getNameForm() {
        return nameForm;
    }

    public void setNameForm(String nameForm) {
        this.nameForm = nameForm;
    }

}

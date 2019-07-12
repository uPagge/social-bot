package org.sadtech.social.bot.service.usercode;

import org.sadtech.social.core.domain.content.Message;

@FunctionalInterface
public interface SaveData<E, C extends Message> {

    E save(C content);

}

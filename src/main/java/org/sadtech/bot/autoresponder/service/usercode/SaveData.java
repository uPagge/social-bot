package org.sadtech.bot.autoresponder.service.usercode;

import org.sadtech.bot.core.domain.content.Message;

@FunctionalInterface
public interface SaveData<E, C extends Message> {

    E save(C content);

}

package org.sadtech.bot.autoresponder.service.usercode;

import org.sadtech.bot.core.domain.content.Content;

@FunctionalInterface
public interface SaveData<E, C extends Content> {

    E save(C content);

}

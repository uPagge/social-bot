package org.sadtech.social.bot.service.save.data;

import org.sadtech.social.core.domain.content.Message;

@FunctionalInterface
public interface PreservableData<E, C extends Message> {

    E getData(C content);

}

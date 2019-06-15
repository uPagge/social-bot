package org.sadtech.bot.autoresponder.service.usercode;

import org.sadtech.bot.core.domain.content.Message;

@FunctionalInterface
public interface CheckData<C extends Message> {

    Boolean checked(C content);

}

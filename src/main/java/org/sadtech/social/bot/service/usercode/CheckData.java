package org.sadtech.social.bot.service.usercode;

import org.sadtech.social.core.domain.content.Message;

@FunctionalInterface
public interface CheckData<C extends Message> {

    Boolean checked(C content);

}

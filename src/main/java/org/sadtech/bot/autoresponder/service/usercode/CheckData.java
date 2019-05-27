package org.sadtech.bot.autoresponder.service.usercode;

import org.sadtech.bot.core.domain.content.Content;

@FunctionalInterface
public interface CheckData<C extends Content> {

    Boolean checked(C content);

}

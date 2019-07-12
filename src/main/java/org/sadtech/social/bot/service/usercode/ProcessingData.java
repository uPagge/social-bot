package org.sadtech.social.bot.service.usercode;

import org.sadtech.social.core.domain.BoxAnswer;
import org.sadtech.social.core.domain.content.Message;

@FunctionalInterface
public interface ProcessingData<C extends Message> {

    BoxAnswer processing(C content);

}

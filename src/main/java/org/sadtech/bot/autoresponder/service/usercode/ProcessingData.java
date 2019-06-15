package org.sadtech.bot.autoresponder.service.usercode;

import org.sadtech.bot.core.domain.BoxAnswer;
import org.sadtech.bot.core.domain.content.Message;

@FunctionalInterface
public interface ProcessingData<C extends Message> {

    BoxAnswer processing(C content);

}

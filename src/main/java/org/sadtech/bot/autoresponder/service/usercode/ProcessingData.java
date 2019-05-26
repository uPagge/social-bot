package org.sadtech.bot.autoresponder.service.usercode;

import org.sadtech.bot.core.domain.BoxAnswer;
import org.sadtech.bot.core.domain.content.Content;

@FunctionalInterface
public interface ProcessingData<C extends Content> {

    BoxAnswer processing(C content);

}

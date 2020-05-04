package org.sadtech.social.bot.service.usercode;

import org.sadtech.social.bot.domain.Clarification;
import org.sadtech.social.core.domain.content.Message;

@FunctionalInterface
public interface ClarificationQuestion<C extends Message> {

    Clarification getClarification(C message);

}

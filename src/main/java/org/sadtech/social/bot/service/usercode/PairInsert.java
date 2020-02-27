package org.sadtech.social.bot.service.usercode;

import org.sadtech.social.bot.utils.Pair;
import org.sadtech.social.core.domain.content.Message;

@FunctionalInterface
public interface PairInsert<C extends Message> {

    Pair<String, String> insert(C content);

}

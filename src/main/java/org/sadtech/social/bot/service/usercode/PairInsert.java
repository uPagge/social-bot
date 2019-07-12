package org.sadtech.social.bot.service.usercode;

import javafx.util.Pair;
import org.sadtech.social.core.domain.content.Message;

@FunctionalInterface
public interface PairInsert<C extends Message> {

    Pair<String, String> insert(C content);

}

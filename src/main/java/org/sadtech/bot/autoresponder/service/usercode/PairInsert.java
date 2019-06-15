package org.sadtech.bot.autoresponder.service.usercode;

import javafx.util.Pair;
import org.sadtech.bot.core.domain.content.Message;

@FunctionalInterface
public interface PairInsert<C extends Message> {

    Pair<String, String> insert(C content);

}

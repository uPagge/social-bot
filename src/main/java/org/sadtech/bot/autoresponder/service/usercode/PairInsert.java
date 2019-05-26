package org.sadtech.bot.autoresponder.service.usercode;

import javafx.util.Pair;
import org.sadtech.bot.core.domain.content.Content;

@FunctionalInterface
public interface PairInsert<C extends Content> {

    Pair<String, String> insert(C content);

}

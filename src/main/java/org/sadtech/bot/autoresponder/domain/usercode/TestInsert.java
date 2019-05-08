package org.sadtech.bot.autoresponder.domain.usercode;

import javafx.util.Pair;

@FunctionalInterface
public interface TestInsert {

    Pair<String, String> insert(Integer userId, String message);

}

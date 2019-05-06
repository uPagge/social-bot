package org.sadtech.bot.autoresponder.domain.usercode;

import java.util.List;

@FunctionalInterface
public interface Insert {

    List<String> insert(Integer userId);

}

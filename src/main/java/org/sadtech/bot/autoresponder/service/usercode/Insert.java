package org.sadtech.bot.autoresponder.service.usercode;

import java.util.List;

@FunctionalInterface
public interface Insert {

    List<String> insert(Integer personId);

}

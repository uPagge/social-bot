package org.sadtech.social.bot.service.usercode;

import java.util.List;

@FunctionalInterface
public interface Insert {

    List<String> insert(Integer personId);

}

package org.sadtech.bot.autoresponder.domain.usercode;

@FunctionalInterface
public interface CheckData {

    Boolean checked(Integer personId, String message);

}

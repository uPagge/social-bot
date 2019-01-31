package org.sadtech.vkbot.autoresponder.entity.usercode;

@FunctionalInterface
public interface CheckData {

    Boolean checked(String data);

}

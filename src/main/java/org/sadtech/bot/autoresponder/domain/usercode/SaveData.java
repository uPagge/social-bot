package org.sadtech.bot.autoresponder.domain.usercode;

@FunctionalInterface
public interface SaveData<E> {

    E save(Integer personId, String message);

}

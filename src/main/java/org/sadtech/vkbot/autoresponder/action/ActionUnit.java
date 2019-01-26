package org.sadtech.vkbot.autoresponder.action;

import org.sadtech.autoresponder.entity.Unit;
import org.sadtech.vkbot.core.entity.Mail;

public interface ActionUnit {

    void action(Unit unit, Mail mail);

}

package org.sadtech.vkbot.autoresponder.timer;

import org.sadtech.autoresponder.entity.Unit;
import org.sadtech.vkbot.autoresponder.action.ActionUnit;
import org.sadtech.vkbot.core.entity.Mail;
import org.sadtech.vkbot.core.entity.Person;

import java.util.TimerTask;

public class TimerVk extends TimerTask {

    private Unit unit;
    private ActionUnit actionUnit;
    private Integer idPerson;

    public TimerVk(ActionUnit actionUnit) {
        this.actionUnit = actionUnit;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public Integer getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(Integer idPerson) {
        this.idPerson = idPerson;
    }

    @Override
    public void run() {
        Mail mail = new Mail();
        Person person = new Person();
        person.setId(idPerson);
        mail.setPerson(person);
        actionUnit.action(unit, mail);
    }


}

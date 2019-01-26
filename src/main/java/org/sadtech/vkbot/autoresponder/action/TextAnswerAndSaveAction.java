package org.sadtech.vkbot.autoresponder.action;

import org.sadtech.autoresponder.entity.Unit;
import org.sadtech.vkbot.autoresponder.entity.TextAnswerAndSave;
import org.sadtech.vkbot.core.entity.Mail;
import org.sadtech.vkbot.core.sender.MailSandler;

public class TextAnswerAndSaveAction implements ActionUnit {

    private MailSandler mailSandler;

    public TextAnswerAndSaveAction(GeneralActionUnit generalActionUnit) {
        generalActionUnit.registerActionUnit(TextAnswerAndSave.class, this);
        this.mailSandler = generalActionUnit.getMailSandler();
    }


    @Override
    public void action(Unit unit, Mail mail) {
        TextAnswerAndSave textAnswerAndSave = (TextAnswerAndSave) unit;

        if (textAnswerAndSave.getPrevUnit()==null) {
            textAnswerAndSave.getSaver().init();
        }

        textAnswerAndSave.getSaver().save(textAnswerAndSave.getKey(), mail.getBody());

        if (!checkNextSaveUnit(textAnswerAndSave)) {
            textAnswerAndSave.getSaver().push();
        }

        if (textAnswerAndSave.getKeyBoard()!=null) {
            mailSandler.send(textAnswerAndSave.getAnswer(), textAnswerAndSave.getKeyBoard().getKeyboard().toString());
        } else {
            mailSandler.send(textAnswerAndSave.getAnswer());
        }
    }

    private boolean checkNextSaveUnit(TextAnswerAndSave unit) {
        if (unit.getNextUnits() == null) {
            return false;
        }
        for (Unit nextUnit : unit.getNextUnits()) {
            if (nextUnit.getClass().equals(TextAnswerAndSave.class)) {
                return true;
            }
        }
        return false;
    }
}

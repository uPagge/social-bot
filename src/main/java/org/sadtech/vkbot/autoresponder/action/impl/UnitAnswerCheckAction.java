//package org.sadtech.vkbot.autoresponder.action.impl;
//
//import org.apache.log4j.Logger;
//import org.sadtech.autoresponder.entity.Unit;
//import org.sadtech.autoresponder.service.PersonService;
//import org.sadtech.vkbot.autoresponder.action.Action;
//import org.sadtech.vkbot.autoresponder.action.ActionUnit;
//import org.sadtech.vkbot.autoresponder.entity.UnitAnswerCheck;
//import org.sadtech.vkbot.core.entity.Mail;
//
//public class UnitAnswerCheckAction implements ActionUnit {
//
//    public static final Logger log = Logger.getLogger(UnitAnswerCheckAction.class);
//    private Action action;
//    private PersonService personService;
//
//    public UnitAnswerCheckAction(Action generalActionUnit) {
//        action = generalActionUnit;
//        generalActionUnit.registerActionUnit(UnitAnswerCheck.class, this);
//        personService = generalActionUnit.getPersonServiceAutoresponder();
//    }
//
//    @Override
//    public void action(Unit unit, Mail mail) {
//        UnitAnswerCheck unitAnswerCheck = (UnitAnswerCheck) unit;
//        unitAnswerCheck.setUserId(mail.getPerson().getId());
//        String string = unitAnswerCheck.getPushData().push();
//        if (string!=null) {
//            unitAnswerCheck.setUserData(string);
//        }
//        if (unitAnswerCheck.getCheck().checked(unitAnswerCheck.getUserData())) {
//            log.info("Проверка пройдена");
//            personService.getPersonById(mail.getPerson().getId()).setUnit(unitAnswerCheck.getUnitTrue());
//            action.action(unitAnswerCheck.getUnitTrue(), mail);
//        } else {
//            log.info("Проверка провалена");
//            personService.getPersonById(mail.getPerson().getId()).setUnit(unitAnswerCheck.getUnitFalse());
//            action.action(unitAnswerCheck.getUnitFalse(), mail);
//        }
//    }
//}

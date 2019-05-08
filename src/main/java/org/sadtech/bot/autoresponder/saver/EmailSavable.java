package org.sadtech.bot.autoresponder.saver;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Map;

public class EmailSavable extends LocalSavable {

    private final EmailConfig emailConfig;

    public EmailSavable(EmailConfig emailConfig) {
        this.emailConfig = emailConfig;
    }

    @Override
    public void push(Integer userId) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<table cellspacing=\"0\" cellpadding=\"0\" width=\"600\" bgcolor=\"#FFFFFF\">\n" +
                " <tbody>\n" +
                " <tr>\n" +
                " <td>\n" +
                " <table cellspacing=\"0\" cellpadding=\"0\" width=\"100%\">\n" +
                " <tbody>\n" +
                " <tr>\n" +
                " <td width=\"149\" valign=\"top\" bgcolor=\"#476695\" style=\"border-radius: 5px 0px 0px 0px;\">&nbsp;</td>\n" +
                " <td width=\"100%\" bgcolor=\"#476695\" valign=\"top\" style=\"border-radius: 0px 5px 0px 0px;\">&nbsp;</td>\n" +
                " </tr>\n" +
                " </tbody>\n" +
                " </table>\n" +
                " </td>\n" +
                " </tr>\n" +
                " <tr>\n" +
                " <td>\n" +
                " <div style=\"padding:18px 18px 13px 18px;border-left:1px solid #dadee3;border-right:1px solid #dadee3;font-size:12px;color:black;\">\n" +
                " <h1 style=\"margin:2px 0px 15px 0;padding:0px 0px 4px;border-bottom:1px solid #D8DFE6;color:#45668E;font-size:100%;\">")
                .append(nameForm)
                .append("</h1>\n" +
                        " <div style=\"line-height:160%;\">\n" +
                        " <table cellspacing=\"0\" cellpadding=\"0\" border=\"0\">\n" +
                        " <tbody>");
        for (Map.Entry<String, String> entry : map.get(userId).entrySet()) {
            stringBuilder.append("<tr>\n" +
                    " <td valign=\"top\" style=\"padding-right:10px;color:#808080\">")
                    .append(entry.getKey())
                    .append(":\n" +
                            " </td>\n" +
                            " <td style=\"padding-bottom:6px\">")
                    .append(entry.getValue())
                    .append("</td></tr>");
        }
        stringBuilder.append("</tbody>\n" +
                " </table>\n" +
                " \n" +
                " </div>\n" +
                " </div>\n" +
                " </td>\n" +
                " </tr>\n" +
                " <tr>\n" +
                " <td>\n" +
                " <table cellspacing=\"0\" cellpadding=\"0\" width=\"100%\">\n" +
                " <tbody>\n" +
                " <tr>\n" +
                " <td width=\"3\">\n" +
                " <div style=\"width:0;height:1px;max-height:1px;line-height:1px;font-size:0;border-left:1px solid #e6e7eb;border-right:1px solid #e8ebed;\"></div>\n" +
                " <div style=\"width:0;height:1px;max-height:1px;line-height:1px;font-size:0;border-left:1px solid #f5f5f5;border-right:1px solid #e6e7eb;\"></div>\n" +
                " </td>\n" +
                " <td width=\"100%\" valign=\"bottom\">\n" +
                " <div style=\"height:1px;max-height:1px;line-height:0;font-size:0;border-bottom:1px solid #dadee3;\">&nbsp;</div>\n" +
                " </td>\n" +
                " <td width=\"3\" align=\"right\">\n" +
                " <div style=\"width:0;height:1px;max-height:1px;line-height:1px;font-size:0;border-left:1px solid #e8ebed;border-right:1px solid #e6e7eb;\"></div>\n" +
                " <div style=\"width:0;height:1px;max-height:1px;line-height:1px;font-size:0;border-left:1px solid #e6e7eb;border-right:1px solid #f5f5f5;\"></div>\n" +
                " </td>\n" +
                " </tr>\n" +
                " </tbody>\n" +
                " </table>\n" +
                " </td>\n" +
                " </tr>\n" +
                " <tr>\n" +
                " <td bgcolor=\"#ffffff\" align=\"center\" style=\"padding:13px 0 0 0;font-size:12px;color:#888888;\"></td>\n" +
                " </tr>\n" +
                " </tbody>\n" +
                " </table>");

        Session session = Session.getDefaultInstance(emailConfig.getProps(), new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(emailConfig.getUsername(), emailConfig.getPassword());
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(emailConfig.getUsername()));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailConfig.getUsername()));
            message.setSubject(nameForm);
            message.setContent(stringBuilder.toString(), "text/html; charset=utf-8");
            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

}

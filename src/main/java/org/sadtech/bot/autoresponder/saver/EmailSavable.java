package org.sadtech.bot.autoresponder.saver;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailSavable extends LocalSavable {

    private final EmailConfig emailConfig;

    public EmailSavable(EmailConfig emailConfig) {
        this.emailConfig = emailConfig;
    }

    @Override
    public void push(Integer userId) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("========= ").append(nameForm).append(" =========\n");
        for (String s : map.get(userId).keySet()) {
            stringBuilder.append(s).append(": ").append(map.get(userId).get(s)).append("\n");
        }
        stringBuilder.append("============================");


        Session session = Session.getDefaultInstance(emailConfig.getProps(), new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(emailConfig.getUsername(), emailConfig.getPassword());
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(emailConfig.getUsername()));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailConfig.getPassword()));
            message.setSubject(nameForm);
            message.setText(stringBuilder.toString());
            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

}

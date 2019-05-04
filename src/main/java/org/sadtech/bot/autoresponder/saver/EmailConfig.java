package org.sadtech.bot.autoresponder.saver;

import java.util.Properties;

public final class EmailConfig {

    private Properties props;
    private String username;
    private String password;

    public Properties getProps() {
        return props;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public class Builder {

        private Builder() {

        }

        public Builder setSmtpHost(String smtpHost) {
            EmailConfig.this.props.setProperty("mail.smtp.host", smtpHost);
            return this;
        }

        public Builder setSmtpSocketFactoryPort(Integer smtpSocketFactoryPortort) {
            EmailConfig.this.props.setProperty("mail.smtp.socketFactory.port", smtpSocketFactoryPortort.toString());
            return this;
        }

        public Builder setSmtpSocketFactoryClass(String smtpSocketFactoryClass) {
            EmailConfig.this.props.setProperty("mail.smtp.socketFactory.class", smtpSocketFactoryClass);
            return this;
        }

        public Builder setSmtpAuth(Boolean smtpAuth) {
            EmailConfig.this.props.setProperty("mail.smtp.auth", (smtpAuth) ? "true" : "false");
            return this;
        }

        public Builder setSmtpPort(Integer smtpPort) {
            EmailConfig.this.props.setProperty("mail.smtp.port", smtpPort.toString());
            return this;
        }

        public Builder setUsername(String username) {
            EmailConfig.this.username = username;
            return this;
        }

        public Builder setPassword(String password) {
            EmailConfig.this.password = password;
            return this;
        }

    }
}

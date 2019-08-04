package org.sadtech.social.bot.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Конфигуратор для использования в {@link org.springframework.context.annotation.Import}.
 *
 * @author upagge [28/07/2019]
 */
@EnableJpaRepositories(basePackages = "org.sadtech.social.bot.service.save.jpa")
@EntityScan(basePackages = "org.sadtech.social.bot.service.save.jpa")
public class SocialBotJpaSpringConfig {

}

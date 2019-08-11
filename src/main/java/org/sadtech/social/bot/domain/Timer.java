package org.sadtech.social.bot.domain;

import lombok.Builder;
import lombok.Data;
import org.sadtech.social.bot.domain.unit.MainUnit;
import org.sadtech.social.bot.service.usercode.CheckData;
import org.sadtech.social.core.utils.Description;

import java.time.LocalDateTime;

/**
 * Используется для отложенной активации обработки Unit-ов.
 *
 * @author upagge [11/07/2019]
 */
@Builder
@Data
public class Timer {

    @Description("Идентификатор таймера")
    private Integer id;

    @Description("Unit, обработка которого откладывается")
    private MainUnit unitAnswer;

    @Description("Unit, который будет обработан после удаления таймера")
    private MainUnit unitDeath;

    @Description("Идентификатор пользователя")
    private Integer personId;

    @Description("Время активации таймера")
    private LocalDateTime timeActive;

    @Description("Время смерти таймера")
    private LocalDateTime timeDeath;

    @Description("Интервал срабатывания таймера")
    private Integer periodSec;

    @Description("Условие срабатывания таймера")
    private CheckData checkLoop;

}

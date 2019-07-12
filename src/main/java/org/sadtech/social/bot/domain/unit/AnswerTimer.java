package org.sadtech.social.bot.domain.unit;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.sadtech.social.bot.service.usercode.CheckData;
import org.sadtech.social.core.utils.Description;

/**
 * Обработчик таймер, позволяющий отложить обработку других Unit-ов.
 *
 * @author upagge [08/07/2019]
 */
@Getter
@EqualsAndHashCode(callSuper = true)
@ToString
public class AnswerTimer extends MainUnit {

    @Description("Unit обработку которого необходимо отложить")
    private MainUnit unitAnswer;

    @Description("Задержка обработки в секундах")
    private Integer timeDelaySec;

    @Description("Время, через которое таймер будет удален в секундах")
    private Integer timeDeathSec;

    @Description("Условие срабатывания отложенного Unit")
    private CheckData checkLoop;

    public AnswerTimer() {
        activeType = UnitActiveType.AFTER;
        typeUnit = TypeUnit.TIMER;
    }

    public static Builder builder() {
        return new AnswerTimer().new Builder();
    }

    public class Builder {

        private Builder() {

        }

        public Builder unitAnswer(MainUnit unitAnswer) {
            AnswerTimer.this.unitAnswer = unitAnswer;
            return this;
        }

        public Builder timeDelaySec(Integer sec) {
            AnswerTimer.this.timeDelaySec = sec;
            return this;
        }

        public Builder checkLoop(CheckData checkLoop) {
            AnswerTimer.this.checkLoop = checkLoop;
            return this;
        }

        public Builder timeDeathSec(Integer timeDeathSec) {
            AnswerTimer.this.timeDeathSec = timeDeathSec;
            return this;
        }

        public AnswerTimer build() {
            return AnswerTimer.this;
        }

    }
}

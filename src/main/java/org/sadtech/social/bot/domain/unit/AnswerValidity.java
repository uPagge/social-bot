package org.sadtech.social.bot.domain.unit;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.sadtech.social.bot.service.action.AnswerValidityAction;
import org.sadtech.social.bot.service.save.Savable;
import org.sadtech.social.bot.service.usercode.PairInsert;
import org.sadtech.social.core.utils.Description;

/**
 * Обработка данных со страницы пользователя.
 *
 * @author upagge [11/07/2019]
 */
@EqualsAndHashCode(callSuper = true)
@ToString
@Getter
public class AnswerValidity extends MainUnit {

    @Description("Unit обрабатывается, если пользователь подтверждает данные")
    private MainUnit unitYes;

    @Description("Unit обрабатывается, если пользователь откланяет данные")
    private MainUnit unitNo;

    @Description("Unit обрабатывается, если данные не найдены")
    private MainUnit unitNull;

    @Description("")
    private Savable<String> tempSave;

    @Description("")
    private PairInsert pairInsert;

    public AnswerValidity() {
        typeUnit = TypeUnit.VALIDITY;
        activeType = UnitActiveType.AFTER;
    }

    public static Builder builder() {
        return new AnswerValidity().new Builder();
    }

    public class Builder {

        private Builder() {

        }

        public Builder unitYes(MainUnit unitYes) {
            AnswerValidity.this.unitYes = unitYes;
            AnswerValidity.this.setKeyWords(AnswerValidityAction.WORDS_YES);
            return this;
        }

        public Builder unitNo(MainUnit unitNo) {
            AnswerValidity.this.unitNo = unitNo;
            AnswerValidity.this.setKeyWords(AnswerValidityAction.WORDS_NO);
            return this;
        }

        public Builder unitNull(MainUnit unitNull) {
            AnswerValidity.this.unitNull = unitNull;
            return this;
        }

        public Builder tempSave(Savable<String> savable) {
            AnswerValidity.this.tempSave = savable;
            return this;
        }

        public Builder pairInsert(PairInsert pairInsert) {
            AnswerValidity.this.pairInsert = pairInsert;
            return this;
        }

        public AnswerValidity build() {
            return AnswerValidity.this;
        }


    }

}

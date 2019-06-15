package org.sadtech.bot.autoresponder.domain.unit;

import org.sadtech.bot.autoresponder.domain.AccountAutoCheck;

import java.util.Objects;

public class AnswerAccount extends MainUnit {

    private Integer totalSum;
    private Integer timeHours;
    private AccountAutoCheck autoCheck;

    public AnswerAccount() {
        activeStatus = UnitActiveStatus.AFTER;
        typeUnit = TypeUnit.ACCOUNT;
    }

    public Integer getTotalSum() {
        return totalSum;
    }

    public Integer getTimeHours() {
        return timeHours;
    }

    public AccountAutoCheck getAutoCheck() {
        return autoCheck;
    }

    public static Builder builder() {
        return new AnswerAccount().new Builder();
    }

    public class Builder {
        private Builder() {

        }

        public Builder totalSum(Integer totalSum) {
            AnswerAccount.this.totalSum = totalSum;
            return this;
        }

        public Builder timeHours(Integer timeHours) {
            AnswerAccount.this.timeHours = timeHours;
            return this;
        }

        public Builder autoCheck(AccountAutoCheck autoCheck) {
            AnswerAccount.this.autoCheck = autoCheck;
            return this;
        }

        public AnswerAccount build() {
            return AnswerAccount.this;
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AnswerAccount)) return false;
        if (!super.equals(o)) return false;
        AnswerAccount that = (AnswerAccount) o;
        return Objects.equals(totalSum, that.totalSum) &&
                Objects.equals(timeHours, that.timeHours) &&
                Objects.equals(autoCheck, that.autoCheck);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), totalSum, timeHours, autoCheck);
    }
}

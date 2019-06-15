package org.sadtech.bot.autoresponder.domain;

import org.sadtech.bot.autoresponder.domain.unit.MainUnit;

import java.util.Objects;

public class AccountAutoCheck {

    private MainUnit successfulPayment;
    private MainUnit failedPayment;
    private Integer periodSec;
    private Integer lifeTimeHours;

    private AccountAutoCheck() {

    }

    public MainUnit getSuccessfulPayment() {
        return successfulPayment;
    }

    public MainUnit getFailedPayment() {
        return failedPayment;
    }

    public Integer getPeriodSec() {
        return periodSec;
    }

    public Integer getLifeTimeHours() {
        return lifeTimeHours;
    }

    public static Builder builder() {
        return new AccountAutoCheck().new Builder();
    }

    public class Builder {
        private Builder() {

        }

        public Builder successfulPayment(MainUnit successfulPayment) {
            AccountAutoCheck.this.successfulPayment = successfulPayment;
            return this;
        }

        public Builder failedPayment(MainUnit failedPayment) {
            AccountAutoCheck.this.failedPayment = failedPayment;
            return this;
        }

        public Builder periodSec(Integer periodSec) {
            AccountAutoCheck.this.periodSec = periodSec;
            return this;
        }

        public Builder lifeTimeHours(Integer lifeTimeHours) {
            AccountAutoCheck.this.lifeTimeHours = lifeTimeHours;
            return this;
        }

        public AccountAutoCheck build() {
            return AccountAutoCheck.this;
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AccountAutoCheck)) return false;
        AccountAutoCheck that = (AccountAutoCheck) o;
        return Objects.equals(successfulPayment, that.successfulPayment) &&
                Objects.equals(failedPayment, that.failedPayment) &&
                Objects.equals(periodSec, that.periodSec) &&
                Objects.equals(lifeTimeHours, that.lifeTimeHours);
    }

    @Override
    public int hashCode() {
        return Objects.hash(successfulPayment, failedPayment, periodSec, lifeTimeHours);
    }

    @Override
    public String toString() {
        return "AccountAutoCheck{" +
                "successfulPayment=" + successfulPayment +
                ", failedPayment=" + failedPayment +
                ", periodSec=" + periodSec +
                ", lifeTimeHours=" + lifeTimeHours +
                '}';
    }
}

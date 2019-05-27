package org.sadtech.bot.autoresponder.domain.unit;

import java.util.Objects;

public class AccountAutoCheck {

    private MainUnit successfulPayment;
    private MainUnit failedPayment;
    private Integer periodSec;
    private Long lifetimeHours;

    public MainUnit getSuccessfulPayment() {
        return successfulPayment;
    }

    public void setSuccessfulPayment(MainUnit successfulPayment) {
        this.successfulPayment = successfulPayment;
    }

    public MainUnit getFailedPayment() {
        return failedPayment;
    }

    public void setFailedPayment(MainUnit failedPayment) {
        this.failedPayment = failedPayment;
    }

    public Integer getPeriodSec() {
        return periodSec;
    }

    public void setPeriodSec(Integer periodSec) {
        this.periodSec = periodSec;
    }

    public Long getLifetimeHours() {
        return lifetimeHours;
    }

    public void setLifetimeHours(Long lifetimeHours) {
        this.lifetimeHours = lifetimeHours;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AccountAutoCheck)) return false;
        AccountAutoCheck that = (AccountAutoCheck) o;
        return Objects.equals(successfulPayment, that.successfulPayment) &&
                Objects.equals(failedPayment, that.failedPayment) &&
                Objects.equals(periodSec, that.periodSec) &&
                Objects.equals(lifetimeHours, that.lifetimeHours);
    }

    @Override
    public int hashCode() {
        return Objects.hash(successfulPayment, failedPayment, periodSec, lifetimeHours);
    }
}

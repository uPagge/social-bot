package org.sadtech.bot.autoresponder.domain.unit;

import java.util.Objects;

public class AnswerAccount extends MainUnit {

    private Integer id;
    private Double totalSum;
    private Integer timeHours;
    private AccountAutoCheck autoCheck;

    public AnswerAccount() {
        activeStatus = UnitActiveStatus.AFTER;
        typeUnit = TypeUnit.ACCOUNT;
    }

    public Double getTotalSum() {
        return totalSum;
    }

    public void setTotalSum(Double totalSum) {
        this.totalSum = totalSum;
    }

    public Integer getTimeHours() {
        return timeHours;
    }

    public void setTimeHours(Integer timeHours) {
        this.timeHours = timeHours;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public AccountAutoCheck getAutoCheck() {
        return autoCheck;
    }

    public void setAutoCheck(AccountAutoCheck autoCheck) {
        this.autoCheck = autoCheck;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AnswerAccount)) return false;
        if (!super.equals(o)) return false;
        AnswerAccount that = (AnswerAccount) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(totalSum, that.totalSum) &&
                Objects.equals(timeHours, that.timeHours);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, totalSum, timeHours);
    }
}

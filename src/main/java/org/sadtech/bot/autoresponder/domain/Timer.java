package org.sadtech.bot.autoresponder.domain;

import org.sadtech.bot.autoresponder.domain.unit.MainUnit;
import org.sadtech.bot.autoresponder.service.usercode.CheckData;

import java.time.LocalDateTime;
import java.util.Objects;

public class Timer {

    private Integer id;
    private MainUnit unitAnswer;
    private MainUnit unitDeath;
    private Integer personId;
    private LocalDateTime timeActive;
    private LocalDateTime timeDeath;
    private Integer periodSec;
    private CheckData checkLoop;

    private Timer() {

    }

    public void setUnitAnswer(MainUnit unitAnswer) {
        this.unitAnswer = unitAnswer;
    }

    public void setUnitDeath(MainUnit unitDeath) {
        this.unitDeath = unitDeath;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public void setTimeActive(LocalDateTime timeActive) {
        this.timeActive = timeActive;
    }

    public void setTimeDeath(LocalDateTime timeDeath) {
        this.timeDeath = timeDeath;
    }

    public void setPeriodSec(Integer periodSec) {
        this.periodSec = periodSec;
    }

    public void setCheckLoop(CheckData checkLoop) {
        this.checkLoop = checkLoop;
    }

    public MainUnit getUnitAnswer() {
        return unitAnswer;
    }

    public Integer getPersonId() {
        return personId;
    }

    public LocalDateTime getTimeActive() {
        return timeActive;
    }

    public CheckData getCheckLoop() {
        return checkLoop;
    }

    public Integer getPeriodSec() {
        return periodSec;
    }

    public MainUnit getUnitDeath() {
        return unitDeath;
    }

    public LocalDateTime getTimeDeath() {
        return timeDeath;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public static Builder builder() {
        return new Timer().new Builder();
    }

    public class Builder {
        private Builder() {

        }

        public Builder id(Integer id) {
            Timer.this.id = id;
            return this;
        }

        public Builder unitDeath(MainUnit unitDeath) {
            Timer.this.unitDeath = unitDeath;
            return this;
        }

        public Builder unitAnswer(MainUnit unitAnswer) {
            Timer.this.unitAnswer = unitAnswer;
            return this;
        }

        public Builder personId(Integer personId) {
            Timer.this.personId = personId;
            return this;
        }

        public Builder timeActive(LocalDateTime timeActive) {
            Timer.this.timeActive = timeActive;
            return this;
        }

        public Builder timeDeath(LocalDateTime timeDeath) {
            Timer.this.timeDeath = timeDeath;
            return this;
        }

        public Builder periodSec(Integer periodSec) {
            Timer.this.periodSec = periodSec;
            return this;
        }

        public Builder checkLoop(CheckData checkLoop) {
            Timer.this.checkLoop = checkLoop;
            return this;
        }

        public Timer build() {
            return Timer.this;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Timer)) return false;
        Timer that = (Timer) o;
        return Objects.equals(unitAnswer, that.unitAnswer) &&
                Objects.equals(personId, that.personId) &&
                Objects.equals(timeActive, that.timeActive) &&
                Objects.equals(timeDeath, that.timeDeath) &&
                Objects.equals(periodSec, that.periodSec) &&
                Objects.equals(checkLoop, that.checkLoop);
    }

    @Override
    public int hashCode() {
        return Objects.hash(unitAnswer, personId, timeActive, timeDeath, periodSec, checkLoop);
    }
}

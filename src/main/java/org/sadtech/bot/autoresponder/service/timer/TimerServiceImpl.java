package org.sadtech.bot.autoresponder.service.timer;

import org.sadtech.bot.autoresponder.domain.Timer;
import org.sadtech.bot.autoresponder.repository.TimerRepository;
import org.sadtech.bot.autoresponder.repository.TimerRepositoryMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TimerServiceImpl implements TimerService {

    private static final Logger log = LoggerFactory.getLogger(TimerServiceImpl.class);
    private final TimerRepository timerRepository;

    public TimerServiceImpl(TimerRepositoryMap timerActionRepository) {
        this.timerRepository = timerActionRepository;
    }

    public TimerRepository getTimerRepository() {
        return timerRepository;
    }

    public List<Timer> getTimerActive(LocalDateTime time) {
        return new ArrayList<>(timerRepository.getTimerActive(time));
    }

    public Integer add(Timer timer) {
        log.info("Таймер установлен: {}", timer);
        return timerRepository.add(timer);
    }

    public void remove(Integer id) {
        log.info("Таймер удален");
        timerRepository.remove(id);
    }

    public void edit(Integer id, Timer timer) {
        timer.setId(id);
        timerRepository.edit(timer);
    }

}

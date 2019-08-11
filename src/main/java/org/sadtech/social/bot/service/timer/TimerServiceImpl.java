package org.sadtech.social.bot.service.timer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.sadtech.social.bot.domain.Timer;
import org.sadtech.social.bot.repository.TimerRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class TimerServiceImpl implements TimerService {

    private final TimerRepository timerRepository;

    public TimerRepository getTimerRepository() {
        return timerRepository;
    }

    @Override
    public List<Timer> getTimerActive() {
        return new ArrayList<>(timerRepository.getTimerActive(LocalDateTime.now()));
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

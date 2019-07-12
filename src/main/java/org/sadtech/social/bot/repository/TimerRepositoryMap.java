package org.sadtech.social.bot.repository;

import org.sadtech.social.bot.domain.Timer;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Локальная реализация репозитория {@link TimerRepository} на основе HashMap.
 *
 * @author upagge [11/07/2019]
 */
public class TimerRepositoryMap implements TimerRepository {

    private final Map<Integer, Timer> map = new HashMap<>();
    private Integer count = 0;

    public Integer add(Timer timer) {
        timer.setId(count);
        map.put(count, timer);
        return count++;
    }

    @Override
    public Collection<Timer> getTimerActive(LocalDateTime time) {
        return map.values().parallelStream().filter(timer -> time.isAfter(timer.getTimeActive())).collect(Collectors.toSet());
    }

    @Override
    public void remove(Integer id) {
        map.remove(id);
    }

    @Override
    public void edit(Timer timer) {
        map.put(timer.getId(), timer);
    }

}

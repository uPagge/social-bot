package org.sadtech.bot.autoresponder.repository;

import org.sadtech.bot.autoresponder.domain.Timer;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

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

    public void remove(Integer id) {
        map.remove(id);
    }

    @Override
    public void edit(Timer timer) {
        map.put(timer.getId(), timer);
    }

}

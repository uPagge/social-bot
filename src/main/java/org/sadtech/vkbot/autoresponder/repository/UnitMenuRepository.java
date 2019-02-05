package org.sadtech.vkbot.autoresponder.repository;

import org.sadtech.autoresponder.entity.Unit;
import org.sadtech.autoresponder.repository.UnitRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UnitMenuRepository implements UnitRepository {

    private Set<Unit> units = new HashSet<>();

    @Override
    public void addUnit(Unit unit) {
        units.add(unit);
    }

    @Override
    public void addUnits(List<Unit> units) {
        this.units.addAll(units);
    }

    @Override
    public Set<Unit> menuUnits() {
        return units;
    }
}

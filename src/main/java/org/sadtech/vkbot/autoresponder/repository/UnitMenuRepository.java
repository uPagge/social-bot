package org.sadtech.vkbot.autoresponder.repository;

import org.sadtech.autoresponder.entity.Unit;
import org.sadtech.autoresponder.repository.UnitRepository;

import java.util.ArrayList;
import java.util.List;

public class UnitMenuRepository implements UnitRepository {

    private List<Unit> units = new ArrayList<>();

    @Override
    public void addUnit(Unit unit) {
        units.add(unit);
    }

    @Override
    public void addUnits(List<Unit> units) {
        this.units.addAll(units);
    }

    @Override
    public List<Unit> menuUnits() {
        return units;
    }
}

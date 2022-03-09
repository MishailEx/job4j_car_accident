package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.AccidentMem;

import java.util.HashMap;

@Service
public class AccidentService implements AccidentStore {
    private AccidentMem accidents = AccidentMem.instOf();

    @Override
    public void create(Accident accident) {
        accident.setId(accidents.getNum().incrementAndGet());
        accidents.getAccidents().put(accident.getId(), accident);
    }

    @Override
    public HashMap<Integer, Accident> getAllAccident() {
        return accidents.getAccidents();
    }

    @Override
    public Accident findById(int id) {
        return accidents.getAccidents().get(id);
    }

    public void updateAccident(int id, Accident accident) {
        accident.setId(id);
        accidents.getAccidents().put(id, accident);
    }
}

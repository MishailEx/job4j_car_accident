package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentJdbcTemplate;
import ru.job4j.accident.repository.AccidentMem;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

@Service
public class AccidentService implements AccidentStore {
    private AccidentJdbcTemplate accidents;

    public AccidentService(AccidentJdbcTemplate accidents) {
        this.accidents = accidents;
    }

    @Override
    public void create(Accident accident, String[] idRules, int idType) {
        accidents.setType(idType, accident);
        accidents.save(accident);
    }

    @Override
    public HashMap<Integer, Accident> getAll() {
        return accidents.getAccidents();
    }

    @Override
    public Accident findById(int id) {
        return accidents.getAccidents().get(id);
    }

    @Override
    public void updateAccident(int id, Accident accident, int idType) {
        accident.setId(id);
        accidents.setType(idType, accident);
        accidents.update(accident);
    }

    @Override
    public List<AccidentType> accidentTypes() {
        return accidents.getAccidentTypeList();
    }

    @Override
    public List<Rule> rules() {
        return accidents.getRules();
    }


}

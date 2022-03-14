package ru.job4j.accident.service;

import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

public interface AccidentStore {
    void create(Accident accident, String[] idRules, int idType);
    HashMap<Integer, Accident> getAll();
    Accident findById(int id);
    void updateAccident(int id, Accident accident, int idType);
    List<AccidentType> accidentTypes();
    List<Rule> rules();
}

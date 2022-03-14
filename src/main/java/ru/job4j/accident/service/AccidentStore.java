package ru.job4j.accident.service;

import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

public interface AccidentStore {
    void create(Accident accident);
    HashMap<Integer, Accident> getAllAccident();
    Accident findById(int id);
    List<AccidentType> accidentTypes();
    List<Rule> rules();
}

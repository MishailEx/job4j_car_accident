package ru.job4j.accident.service;

import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;

import java.util.HashMap;
import java.util.List;

public interface AccidentStore {
    void create(Accident accident);
    HashMap<Integer, Accident> getAllAccident();
    Accident findById(int id);
    List<AccidentType> accidentTypes();
}

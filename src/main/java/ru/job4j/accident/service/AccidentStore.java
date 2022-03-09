package ru.job4j.accident.service;

import ru.job4j.accident.model.Accident;

import java.util.HashMap;

public interface AccidentStore {
    void create(Accident accident);
    HashMap<Integer, Accident> getAllAccident();
    Accident findById(int id);
}

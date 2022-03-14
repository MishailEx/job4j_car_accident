package ru.job4j.accident.service;

import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public interface AccidentStore {
    void create(Accident accident, HttpServletRequest req);
    HashMap<Integer, Accident> getAll();
    Accident findById(int id);
    void updateAccident(int id, Accident accident, int idType);
    List<AccidentType> accidentTypes();
    List<Rule> rules();
    Accident setRules(HttpServletRequest req, Accident accident);
}

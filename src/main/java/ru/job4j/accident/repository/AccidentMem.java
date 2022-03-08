package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.util.HashMap;

@Repository
public class AccidentMem {
    private HashMap<Integer, Accident> accidents = new HashMap<>();
    private int num = 1;

    public void create(Accident accident) {
        accidents.put(num++, accident);
    }

    public HashMap<Integer, Accident> getAllAccident() {
        return accidents;
    }

    public Accident findById(int id) {
        return accidents.get(id);
    }
}

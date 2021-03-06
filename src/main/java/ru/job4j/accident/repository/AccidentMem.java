package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem {
    private HashMap<Integer, Accident> accidents = new HashMap<>();
    private AtomicInteger num = new AtomicInteger(0);
    private List<AccidentType> accidentTypeList = List.of(
            AccidentType.of(1, "Две машины"),
            AccidentType.of(2, "Машина и человек"),
            AccidentType.of(3, "Машина и велосипед"));
    private List<Rule> rules = List.of(
            Rule.of(1, "Статья. 1"),
            Rule.of(2, "Статья. 2"),
            Rule.of(3, "Статья. 3"));

    public HashMap<Integer, Accident> getAccidents() {
        return accidents;
    }

    public Accident setType(int id, Accident accident) {
        accident.setAccidentType(accidentTypeList.get(id));
        return accident;
    }

    public AtomicInteger getNum() {
        return num;
    }

    public void setNum(AtomicInteger num) {
        this.num = num;
    }

    public void setAccidents(HashMap<Integer, Accident> accidents) {
        this.accidents = accidents;
    }

    public List<AccidentType> getAccidentTypeList() {
        return accidentTypeList;
    }

    public List<Rule> getRules() {
        return rules;
    }
}

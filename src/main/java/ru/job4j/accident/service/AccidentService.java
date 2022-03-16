package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class AccidentService implements AccidentStore {
    private AccidentRepository accidents;

    public AccidentService(AccidentRepository accidents) {
        this.accidents = accidents;
    }

    @Override

    public void create(Accident accident, HttpServletRequest req) {
        int idType = Integer.parseInt(req.getParameter("type.id")) - 1;
        setType(idType, accident);
        accidents.save(accident);
    }

    @Override
    public HashMap<Integer, Accident> getAll() {
        List<Accident> a = (List<Accident>) accidents.findAll();
        HashMap<Integer, Accident> ax = new HashMap<>();
        for (Accident ac: a) {
            ax.put(ac.getId(), ac);
        }
        return ax;
    }

    @Override
    public Accident findById(int id) {
        return accidents.findById(id).get();
    }

    @Override
    public void updateAccident(int id, Accident accident, int idType) {
        accident.setId(id);
        setType(idType, accident);
        accidents.save(accident);
    }

    @Override
    public List<AccidentType> accidentTypes() {
        return accidents.getAccidentTypeList();
    }

    @Override
    public List<Rule> rules() {
        return accidents.getRules();
    }

    @Override
    public Accident setRules(HttpServletRequest req, Accident accident) {
        String[] ids = req.getParameterValues("rIds");
        List<Rule> rsl = new ArrayList<>();
        for (String s: ids) {
            int idR = Integer.parseInt(s) - 1;
            rsl.add(rules().get(idR));
        }
        accident.setRules(rsl);
        return accident;
    }

    @Override
    public Accident setType(int id, Accident accident) {
        accident.setAccidentType(accidents.getAccidentTypeList().get(id));
        return accident;
    }
}

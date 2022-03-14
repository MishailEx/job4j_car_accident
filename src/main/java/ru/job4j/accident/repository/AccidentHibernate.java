package ru.job4j.accident.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

@Repository
public class AccidentHibernate {
    private final SessionFactory sf;

    public AccidentHibernate(SessionFactory sf) {
        this.sf = sf;
    }

    public Accident save(Accident accident) {
        try (Session session = sf.openSession()) {
            session.save(accident);
            return accident;
        }
    }

    public HashMap<Integer, Accident> getAccidents() {
        HashMap<Integer, Accident> rsl = new LinkedHashMap<>();
        List<Accident> list;
        try (Session session = sf.openSession()) {
           list = session.createQuery("from Accident a join"
                    + " fetch a.rules join fetch a.type").list();
        }
        list.forEach(a -> rsl.put(a.getId(), a));
        return rsl;
    }

    public List<Rule> getRules() {
        try (Session session = sf.openSession()) {
            return session
                    .createQuery("from Rule", Rule.class)
                    .list();
        }
    }

    public List<AccidentType> getAccidentTypeList() {
        try (Session session = sf.openSession()) {
            return session
                    .createQuery("from AccidentType", AccidentType.class)
                    .list();
        }
    }

    public Accident update(Accident accident) {
        try (Session session = sf.openSession()) {
            session.update(accident);
            return accident;
        }
    }

    public Accident setType(int id, Accident accident) {
        accident.setAccidentType(getAccidentTypeList().get(id));
        return accident;
    }
}

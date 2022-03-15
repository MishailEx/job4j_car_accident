package ru.job4j.accident.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.function.Function;

@Repository
public class AccidentHibernate {
    private final SessionFactory sf;

    public AccidentHibernate(SessionFactory sf) {
        this.sf = sf;
    }

    private <T> T tx(final Function<Session, T> command) {
        final Session session = sf.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            T rsl = command.apply(session);
            tx.commit();
            return rsl;
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }


    public Accident save(Accident accident) {
        this.tx(session -> session.save(accident));
        return accident;
    }

    public HashMap<Integer, Accident> getAccidents() {
        HashMap<Integer, Accident> rsl = new LinkedHashMap<>();
        List<Accident> list = this.tx(session -> session.createQuery("from Accident a join"
                + " fetch a.rules join fetch a.type").list());
        list.forEach(a -> rsl.put(a.getId(), a));
        return rsl;
    }

    public List<Rule> getRules() {
        return this.tx(session -> session
                .createQuery("from Rule", Rule.class)
                .list());
    }

    public List<AccidentType> getAccidentTypeList() {
        return this.tx(session -> session
                .createQuery("from AccidentType", AccidentType.class)
                .list());
    }

    public Accident update(Accident accident) {
        this.tx(session -> {
            session.update(accident);
            return accident;
        });
        return null;
    }


    public Accident setType(int id, Accident accident) {
        accident.setAccidentType(getAccidentTypeList().get(id));
        return accident;
    }
}

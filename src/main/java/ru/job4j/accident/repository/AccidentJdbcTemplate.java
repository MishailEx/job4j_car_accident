package ru.job4j.accident.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

@Repository
public class AccidentJdbcTemplate {
    private final JdbcTemplate jdbc;

    public AccidentJdbcTemplate(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public Accident save(Accident accident) {
        jdbc.update("insert into accident (name, text, address, typeId) values (?, ?, ?, ?)",
                accident.getName(), accident.getText(), accident.getAddress(), accident.getAccidentType().getId());
        return accident;
    }

    public HashMap<Integer, Accident> getAccidents() {
        HashMap<Integer, Accident> rsl = new LinkedHashMap<>();
        List<Accident> list = jdbc.query("SELECT a.id,a.name,a.text,a.address, "
                        + "b.id as idType, b.name as nameType from  accident a\n" +
                        "INNER JOIN accidentType b ON a.typeid = b.id",
                (rs, row) -> {
                    Accident accident = new Accident();
                    accident.setId(rs.getInt("id"));
                    accident.setName(rs.getString("name"));
                    accident.setText(rs.getString("text"));
                    accident.setAddress(rs.getString("address"));
                    accident.setAccidentType(AccidentType.of(rs.getInt("idType"), rs.getString("nameType")));
                    return accident;
                });
        for (Accident a: list) {
            List<Rule> rules = jdbc.query("SELECT a.rulesId, b.id, r.name as ruleName from accident_rules a " +
                            "JOIN accident b ON a.accidentId = b.id " +
                            "join rules r on a.rulesId = r.id where a.accidentId = 1",
                    (rs, row) -> {
                        Rule rule = new Rule();
                        rule.setId(rs.getInt("rulesId"));
                        rule.setName(rs.getString("ruleName"));
                        return rule;
                    });
            a.setRules(rules);
        }
        list.forEach(a -> rsl.put(a.getId(), a));
        return rsl;
    }

    public List<Rule> getRules() {
        return jdbc.query("select * from rules",
                (rs, row) -> {
                    Rule rule = new Rule();
                    rule.setId(rs.getInt("id"));
                    rule.setName(rs.getString("name"));
                    return rule;
                });
    }

    public List<AccidentType> getAccidentTypeList() {
        return jdbc.query("select * from accidentType",
                (rs, row) -> {
                    AccidentType type = new AccidentType();
                    type.setId(rs.getInt("id"));
                    type.setName(rs.getString("name"));
                    return type;
                });
    }

    public Accident update(Accident accident) {
        jdbc.update( "update accident set name = ?, text = ?, address = ?, typeId = ? where id = ?",
                accident.getName(), accident.getText(),
                accident.getAddress(), accident.getAccidentType().getId(),
                accident.getId());
        jdbc.update("delete from accident_rules where accidentId = ?", accident.getId());
        for (Rule r: accident.getRules()) {
            jdbc.update("insert into accident_rules (accidentId, rulesId) values (?, ?)",
                    accident.getId(), r.getId());
        }
        return accident;
    }

    public Accident setType(int id, Accident accident) {
        accident.setAccidentType(getAccidentTypeList().get(id));
        return accident;
    }
}
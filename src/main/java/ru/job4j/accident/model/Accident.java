package ru.job4j.accident.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "accident")
public class Accident {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String text;
    private String address;
    @ManyToOne
    @JoinColumn(name = "typeId")
    private AccidentType type;
    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name = "accident_rules",
            joinColumns = @JoinColumn(name = "accidentId"),
            inverseJoinColumns = @JoinColumn(name = "rulesId"))
    private List<Rule> rules = new ArrayList<>();

    public Accident() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public AccidentType getAccidentType() {
        return type;
    }

    public void setAccidentType(AccidentType accidentType) {
        this.type = accidentType;
    }

    public List<Rule> getRules() {
        return rules;
    }

    public void setRules(List<Rule> rules) {
        this.rules = rules;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Accident accident = (Accident) o;
        return id == accident.id && Objects.equals(name, accident.name) && Objects.equals(text, accident.text) && Objects.equals(address, accident.address) && Objects.equals(type, accident.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, text, address, type);
    }

    @Override
    public String
    toString() {
        return "Accident{ id=" + id + ", name='" + name + '\'' + ", text='" + text + '\''
                + ", address='" + address + '\'' + ", accidentType=" + type + '}';
    }
}

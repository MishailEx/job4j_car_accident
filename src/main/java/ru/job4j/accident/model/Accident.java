package ru.job4j.accident.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.persistence.*;
import java.util.*;

@Entity
@NamedEntityGraphs({
        @NamedEntityGraph(name = "Accident-load",
                attributeNodes = {
                @NamedAttributeNode("images"),
                @NamedAttributeNode("rules")}),
        @NamedEntityGraph(name = "Accident-images",
                attributeNodes = {
                @NamedAttributeNode("images")})
})
@Table(name = "accident")
public class Accident {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String text;
    private String address;
    private String author;

    @OneToOne
    private Status status;

    @ManyToOne
    @JoinColumn(name = "typeId")
    private AccidentType type;

    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Rule> rules = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "accident_id")
    private Set<Image> images = new HashSet<>();

    public Accident() {
    }

    @ModelAttribute("accident")
    public Accident getAccident() {
        return new Accident();
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Set<Image> getImages() {
        return images;
    }

    public void setImages(Set<Image> images) {
        this.images = images;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Accident accident = (Accident) o;
        return id == accident.id && Objects.equals(name, accident.name) && Objects.equals(text, accident.text) && Objects.equals(address, accident.address) && Objects.equals(author, accident.author) && Objects.equals(status, accident.status) && Objects.equals(type, accident.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, text, address, author, status, type);
    }

    @Override
    public String toString() {
        return "Accident{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", text='" + text + '\'' +
                ", address='" + address + '\'' +
                ", author='" + author + '\'' +
                ", status=" + status +
                ", type=" + type +
                '}';
    }
}

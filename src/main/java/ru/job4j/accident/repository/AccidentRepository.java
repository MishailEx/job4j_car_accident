package ru.job4j.accident.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.List;

public interface AccidentRepository extends CrudRepository<Accident, Integer>{
    @Query("from AccidentType")
    List<AccidentType> getAccidentTypeList();
    @Query("from Rule")
    List<Rule> getRules();
}

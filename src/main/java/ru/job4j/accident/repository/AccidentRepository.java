package ru.job4j.accident.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccidentRepository extends CrudRepository<Accident, Integer> {

    @Override
    @EntityGraph(value = "Accident-images", type = EntityGraph.EntityGraphType.LOAD)
    List<Accident> findAll();

    void deleteById(int id);

    @Override
    @EntityGraph(value = "Accident-images", type = EntityGraph.EntityGraphType.LOAD)
    Optional<Accident> findById(Integer integer);
}

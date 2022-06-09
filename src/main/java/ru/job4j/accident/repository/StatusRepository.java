package ru.job4j.accident.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Status;

@Repository
public interface StatusRepository extends CrudRepository<Status, Integer> {
    Status findByName(String name);
}

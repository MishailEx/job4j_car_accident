package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.repository.AccidentTypeRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class AccidentTypeService {
    private AccidentTypeRepository repository;

    public AccidentTypeService(AccidentTypeRepository repository) {
        this.repository = repository;
    }

    public List<AccidentType> findAll() {
        return StreamSupport.stream(repository.findAll()
                .spliterator(), false).collect(Collectors.toList());
    }
}

package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.model.Status;
import ru.job4j.accident.repository.RuleRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class RuleService {
    private RuleRepository repository;

    public RuleService(RuleRepository repository) {
        this.repository = repository;
    }

    public List<Rule> findAll() {
        return StreamSupport.stream(repository.findAll()
                .spliterator(), false).collect(Collectors.toList());
    }
}

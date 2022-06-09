package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Status;
import ru.job4j.accident.repository.StatusRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class StatusService {
    private StatusRepository statusRepository;

    public StatusService(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    public Status findByName(String name) {
       return statusRepository.findByName("accepted");
    }

    public List<Status> findAll() {
       return StreamSupport.stream(statusRepository.findAll()
                .spliterator(), false).collect(Collectors.toList());
    }

    public Status findById(int id) {
       return statusRepository.findById(id).get();
    }
}

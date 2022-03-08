package ru.job4j.accident.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.accident.model.Accident;

import java.util.List;

@Controller
public class IndexControl {
    @GetMapping("/")
    public String index(Model model) {
        List<Accident> accidents = List.of(new Accident("oleg", "parking", "lenina 62"),
                new Accident("vlad", "parking", "truhina 62"));
        model.addAttribute("accidents", accidents);
        return "index";
    }
}
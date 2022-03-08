package ru.job4j.accident.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.accident.model.Accident;

import java.util.HashMap;
import java.util.LinkedHashMap;

@Controller
public class IndexControl {
    @GetMapping("/")
    public String index(Model model) {
        HashMap<Integer, Accident> accidents = new LinkedHashMap<>();
        accidents.put(1,new Accident("aa", "as", "rr"));
        accidents.put(2,new Accident("pp", "ii", "yy"));
        model.addAttribute("accidents", accidents);
        return "index";
    }
}
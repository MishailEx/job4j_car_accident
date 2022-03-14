package ru.job4j.accident.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.service.AccidentService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class AccidentControl {
    private AccidentService accidentService;

    public AccidentControl(AccidentService accidentService) {
        this.accidentService = accidentService;
    }

    @GetMapping("/create")
    public String create(Model model) {
        List<AccidentType> types = accidentService.accidentTypes();
        model.addAttribute("types", types);
        return "accident/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident, @RequestParam("type.id") int id) {
        accident.setAccidentType(AccidentType.of(id, null));
        accidentService.create(accident);
        return "redirect:/";
    }

    @GetMapping("/update")
    public String update(@RequestParam("id") int id, Model model) {
        model.addAttribute("accident", accidentService.findById(id));
        List<AccidentType> types = accidentService.accidentTypes();
        model.addAttribute("types", types);
        return "accident/update";
    }

    @PostMapping("/update")
    public String update(HttpServletRequest req, @ModelAttribute Accident accident) {
        int id = Integer.parseInt(req.getParameter("id"));
        int idType = Integer.parseInt(req.getParameter("type.id"));
        accident.setAccidentType(AccidentType.of(idType, null));
        accidentService.updateAccident(id, accident);
        return "redirect:/";
    }
}

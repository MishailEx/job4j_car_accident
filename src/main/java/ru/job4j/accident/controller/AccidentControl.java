package ru.job4j.accident.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.service.AccidentService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
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
        List<Rule> ruleSet = accidentService.rules();
        model.addAttribute("types", types);
        model.addAttribute("rules", ruleSet);
        return "accident/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident, HttpServletRequest req) {
        Accident acc = setParam(req, accident);
        accidentService.create(acc, req.getParameterValues("rIds"));
        return "redirect:/";
    }

    @GetMapping("/update")
    public String update(@RequestParam("id") int id, Model model) {
        model.addAttribute("accident", accidentService.findById(id));
        List<AccidentType> types = accidentService.accidentTypes();
        List<Rule> ruleSet = accidentService.rules();
        model.addAttribute("types", types);
        model.addAttribute("rules", ruleSet);
        return "accident/update";
    }

    @PostMapping("/update")
    public String update(HttpServletRequest req, @ModelAttribute Accident accident) {
        int id = Integer.parseInt(req.getParameter("id"));
        Accident acc = setParam(req, accident);
        accidentService.updateAccident(id, acc);
        return "redirect:/";
    }

    private Accident setParam(HttpServletRequest req, Accident accident) {
        int idType = Integer.parseInt(req.getParameter("type.id")) - 1;
        accident.setAccidentType(accidentService.accidentTypes().get(idType));
        String[] ids = req.getParameterValues("rIds");
        List<Rule> rules = new ArrayList<>();
        for (String s: ids) {
            int idR = Integer.parseInt(s) - 1;
            rules.add(accidentService.rules().get(idR));
        }
        accident.setRules(rules);
        return accident;
    }
}

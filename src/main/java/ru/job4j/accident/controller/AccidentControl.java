package ru.job4j.accident.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import ru.job4j.accident.domian.ViewAccident;
import ru.job4j.accident.service.*;

import javax.servlet.http.HttpServletRequest;


@Controller
public class AccidentControl {
    private final AccidentService accidentService;
    private final RuleService ruleService;
    private final AccidentTypeService typeService;
    private final StatusService statusService;

    @Value("${server.port}")
    private String port;

    public AccidentControl(AccidentService accidentService, RuleService ruleService,
                           AccidentTypeService typeService, StatusService statusService) {
        this.accidentService = accidentService;
        this.ruleService = ruleService;
        this.typeService = typeService;
        this.statusService = statusService;
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("types", typeService.findAll());
        model.addAttribute("rules", ruleService.findAll());
        return "accident/create";
    }

    @PostMapping("/save")
    public String save(HttpServletRequest req,
                       @RequestParam("file") CommonsMultipartFile[] files) {
        accidentService.create(req, files);
        return "redirect:/";
    }

    @GetMapping("/update")
    public String update(@RequestParam("id") String id, Model model) {
        model.addAttribute("accident", accidentService.findById(Integer.parseInt(id)));
        model.addAttribute("port", port);
        model.addAttribute("types", typeService.findAll());
        model.addAttribute("rules", ruleService.findAll());
        model.addAttribute("statuses", statusService.findAll());
        model.addAttribute("user", UserService.getPrincipalUser());
        return "accident/update";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute ViewAccident accident, HttpServletRequest req,
                         @RequestParam("file") CommonsMultipartFile[] files) {
        accidentService.updateAccident(accident, req, files);
        return "redirect:/";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") String id) {
        accidentService.delete(Integer.parseInt(id));
        return "redirect:/";
    }
}

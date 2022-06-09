package ru.job4j.accident.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.accident.service.AccidentService;
import ru.job4j.accident.service.UserService;

@Controller
public class IndexControl {

    @Value("${dir.upload}")
    private String dirUpload;

    @Value("${server.port}")
    private String port;

    private final AccidentService accidents;

    public IndexControl(AccidentService accidents) {
        this.accidents = accidents;
    }


    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("user", UserService.getPrincipalUser());
        model.addAttribute("accidents", accidents.getAll());
        model.addAttribute("dirUpload", dirUpload);
        model.addAttribute("port", port);
        return "index";
    }
}
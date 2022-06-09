package ru.job4j.accident.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import ru.job4j.accident.domian.ViewAccident;
import ru.job4j.accident.model.*;
import ru.job4j.accident.repository.AccidentRepository;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

@Service
public class AccidentService {
    private AccidentRepository accidents;
    private StatusService statusService;
    private RuleService ruleService;
    private AccidentTypeService accidentTypeService;

    @Value("${dir.upload}")
    private String pathUpload;

    public AccidentService(AccidentRepository accidents, StatusService statusService,
                           RuleService ruleService, AccidentTypeService accidentTypeService) {
        this.accidents = accidents;
        this.statusService = statusService;
        this.ruleService = ruleService;
        this.accidentTypeService = accidentTypeService;
    }

    public void create(HttpServletRequest req, CommonsMultipartFile[] files) {
        Accident accident = new Accident();
        accident.setName(req.getParameter("name"));
        accident.setText(req.getParameter("text"));
        accident.setAddress(req.getParameter("address"));
        setType(req, accident);
        setRules(req, accident);
        accident.setAuthor(UserService.getCurrentUsername());
        accident.setStatus(statusService.findByName("принята"));
        fileUpload(accident, files);
        accidents.save(accident);
    }

    private Accident fileUpload(Accident accident, CommonsMultipartFile[] files) {
        if (!files[0].getOriginalFilename().isEmpty()) {
            File uploadDir = new File(pathUpload);
            if (!uploadDir.exists()) {
                try {
                    Files.createDirectory(Path.of(pathUpload));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            for (CommonsMultipartFile file: files) {
                String uuidFile = UUID.randomUUID().toString();
                String fileName = uuidFile + "." + file.getOriginalFilename();
                try {
                    file.transferTo(new File(pathUpload  + "/" + fileName));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Image image = new Image();
                image.setName(fileName);
                accident.getImages().add(image);
            }
        }
        return accident;
    }

    public HashMap<Integer, Accident> getAll() {
        List<Accident> a = (List<Accident>) accidents.findAll();
        HashMap<Integer, Accident> ax = new HashMap<>();
        for (Accident ac : a) {
            ax.put(ac.getId(), ac);
        }
        return ax;
    }

    public Accident findById(int id) {
        return accidents.findById(id).get();
    }

    public Accident setRules(HttpServletRequest req, Accident accident) {
        String[] ids = req.getParameterValues("rIds");
        accident.getRules().clear();
        for (String s : ids) {
            int idR = Integer.parseInt(s) - 1;
            accident.getRules().add(ruleService.findAll().get(idR));
        }
        return accident;
    }

    public Accident setType(HttpServletRequest req, Accident accident) {
        int idType = Integer.parseInt(req.getParameter("type.id")) - 1;
        accident.setAccidentType(accidentTypeService.findAll().get(idType));
        return accident;
    }

    public void updateAccident(ViewAccident vAccident, HttpServletRequest req, CommonsMultipartFile[] files) {
        int idStatus = Integer.parseInt(req.getParameter("status"));
        Accident accident = findById(vAccident.getId());
        accident.setText(vAccident.getText());
        accident.setAddress(vAccident.getAddress());
        accident.setName(vAccident.getName());
        setType(req, accident);
        setRules(req, accident);
        fileUpload(accident, files);
        accident.setStatus(statusService.findById(idStatus));
        accidents.save(accident);
    }

    public void delete(int id) {
        accidents.deleteById(id);
    }
}

package ru.job4j.accident.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.accident.service.ImageService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Controller
public class ImageControl {

    private ImageService service;

    public ImageControl(ImageService service) {
        this.service = service;
    }

    @GetMapping("/downloadImg")
    public void show(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        File downloadFile = service.showImg(req);
        resp.setContentType("application/octet-stream");
        resp.setHeader("Content-Disposition", "attachment; filename=\"" + downloadFile.getName() + "\"");
        FileInputStream stream = new FileInputStream(downloadFile);
        resp.getOutputStream().write(stream.readAllBytes());
    }
}

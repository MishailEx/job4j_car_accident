package ru.job4j.accident.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@Service
public class ImageService {

    @Value("${dir.upload}")
    private String dirUpload;

    public ImageService() {
    }

    public File showImg(HttpServletRequest req) {
        String name = req.getParameter("nameImg");
        String path = dirUpload + "/" + name;
        if (!new File(path).exists()) {
            path = dirUpload + "/default";
        }
        return new File(path);
    }
}

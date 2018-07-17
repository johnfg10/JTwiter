package io.github.johnfg10.jtweeter.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import io.github.johnfg10.jtweeter.database.model.Announcement;
import io.github.johnfg10.jtweeter.database.repositories.AnnouncementsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.*;

@Controller
public class IndexController {

    @Autowired
    private AnnouncementsRepository announcementsRepository;

    @GetMapping("/fragments")
    public String getfragments(){
        return "fragments/";
    }

    @RequestMapping({"/", "/index", "index.html"})
    public String indexPage(Model model){
        List<Announcement> announcements = new ArrayList<>();
        Date currentDate = Date.from(Instant.now());

        for (Announcement announcement : announcementsRepository.findAll()) {
            // if the expiration date is null we presume that it doesn't expire
            if (announcement.expirationDate != null){
                //if the current date is after the expiration date we will skip it
                if (currentDate.after(announcement.expirationDate)){
                    continue;
                }
            }
            announcements.add(announcement);
        }
        model.addAttribute("announcements", announcements);
        return "index";
    }

}

package ru.zulvit.databasecoursework.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {
    @GetMapping("/")
    public String index(Model model) {
        return "index";
    }

    @GetMapping("/createEvent")
    public String createEvent(Model model) {
        model.addAttribute("title", "Заголовок страницы");
        model.addAttribute("description", "Заголовок страницы");
        model.addAttribute("location", "Заголовок страницы");
        model.addAttribute("start_time", "Заголовок страницы");
        model.addAttribute("end_time", "Заголовок страницы");
        return "create-event";
    }
}

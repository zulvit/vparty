package ru.zulvit.databasecoursework.controller;

import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.zulvit.databasecoursework.dto.EventDTO;
import ru.zulvit.databasecoursework.model.User;
import ru.zulvit.databasecoursework.service.EventService;

import java.util.List;

@Controller
@Log4j2
public class EventController {
    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/createEvent")
    public String createEvent(Model model) {
        model.addAttribute("event", new EventDTO());
        return "create-event";
    }

    @PostMapping("/createEvent")
    public String handleCreateEvent(@Valid @ModelAttribute("event") EventDTO eventDTO, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("event", eventDTO);
            return "create-event";
        }
        System.out.println(eventDTO);
        eventService.createEvent(eventDTO);
        return "redirect:/";
    }


    @GetMapping("/viewEvents")
    public String getEventList(@RequestParam(required = false) String sort, Model model) {
        List<EventDTO> events = eventService.getAllEvents();

        // Здесь вы можете добавить логику сортировки списка мероприятий в зависимости от параметра sort

        model.addAttribute("events", events);
        return "view-events";
    }

//    @GetMapping("/viewEvents")
//    public String viewEvents(Model model) {
//        model.addAttribute("user", new User());
//        return "view-events";
//    }

    @GetMapping("/register")
    public String registerUser(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String handleRegisterUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("user", user);
            return "register";
        }
        System.out.println(user);
//        userService.registerUser(user);
        return "redirect:/login";
    }

}
package ru.zulvit.databasecoursework.controller;

import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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

    @GetMapping("/eventList")
    public String getEventList(@RequestParam(required = false) String sort, Model model) {
        Sort sorting = Sort.unsorted();
        if (sort != null) {
            switch (sort) {
                case "name":
                    sorting = Sort.by("title");
                    break;
                case "date":
                case "time":
                    sorting = Sort.by("startTime");
                    break;
            }
        }
        List<EventDTO> events = eventService.getAllSortedEvents(sorting);
        model.addAttribute("events", events);
        return "view-events";
    }

    @GetMapping("/viewEvents")
    public String getViewEvents(Model model) {
        List<EventDTO> events = eventService.getAllEvents();

        model.addAttribute("events", events);
        return "view-events";
    }

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
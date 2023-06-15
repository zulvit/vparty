package ru.zulvit.databasecoursework.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.zulvit.databasecoursework.dto.EventDTO;
import ru.zulvit.databasecoursework.service.EventService;

@Controller
public class EventDetailsController {
    private final EventService eventService;

    @Autowired
    public EventDetailsController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/eventDetails")
    public String getEventDetails(@RequestParam Long id, Model model) {
        EventDTO event = eventService.getEventById(id);
        model.addAttribute("event", event);
        return "event-details";
    }
}

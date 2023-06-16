package ru.zulvit.databasecoursework.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.zulvit.databasecoursework.dto.EventDTO;
import ru.zulvit.databasecoursework.service.EventService;

@Controller
public class EditEventController {
    private final EventService eventService;

    @Autowired
    public EditEventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/editEvent")
    public String showEditEventForm(@RequestParam("id") Long id, Model model) {
        EventDTO event = eventService.getEventById(id);
        model.addAttribute("event", event);
        return "edit-event";
    }

    @PostMapping("/editEvent")
    public String handleEditEvent(@Valid @ModelAttribute("event") EventDTO eventDTO, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("event", eventDTO);
            return "edit-event";
        }
        eventService.createEvent(eventDTO);
        return "redirect:/viewEvents";
    }
}

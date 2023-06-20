package ru.zulvit.databasecoursework.controller;

import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.zulvit.databasecoursework.dto.EventDTO;
import ru.zulvit.databasecoursework.model.Event;
import ru.zulvit.databasecoursework.service.EventService;

import java.util.List;

@Log4j2
@Controller
@RequestMapping("/event")
public class EventController {
    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/create")
    public String createEvent(Model model) {
        model.addAttribute("event", new EventDTO());
        return "create-event";
    }

    @PostMapping("/create")
    public String handleCreateEvent(@Valid @ModelAttribute("event") EventDTO eventDTO, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("event", eventDTO);
            return "create-event";
        }
        System.out.println(eventDTO);
        eventService.createEvent(eventDTO);
        return "redirect:/";
    }

    @GetMapping("/delete")
    public String deleteEvent(@RequestParam("id") Long eventId) {
        eventService.deleteEvent(eventId);
        return "redirect:/";
    }

    @GetMapping("/list")
    public String getEventList(@RequestParam(required = false) String sort, Model model) {
        Sort sorting = Sort.unsorted();
        if (sort != null) {
            switch (sort) {
                case "name" -> sorting = Sort.by("title");
                case "date", "time" -> sorting = Sort.by("startTime");
            }
        }
        List<EventDTO> events = eventService.getAllSortedEvents(sorting);
        model.addAttribute("events", events);
        return "view-events";
    }

    @GetMapping("/view")
    public String getViewEvents(Model model) {
        List<EventDTO> events = eventService.getAllEvents();

        model.addAttribute("events", events);
        return "view-events";
    }

    @GetMapping("/search")
    public String search(@RequestParam("term") String term, Model model) {
        List<Event> events = eventService.searchEvents(term);
        model.addAttribute("events", events);
        return "search-results";
    }
}
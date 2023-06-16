package ru.zulvit.databasecoursework.controller;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.zulvit.databasecoursework.dto.EventDTO;
import ru.zulvit.databasecoursework.dto.MessageDTO;
import ru.zulvit.databasecoursework.mapper.EventMapper;
import ru.zulvit.databasecoursework.model.Event;
import ru.zulvit.databasecoursework.model.User;
import ru.zulvit.databasecoursework.service.EventService;
import ru.zulvit.databasecoursework.service.MessageService;
import ru.zulvit.databasecoursework.service.UserService;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class EventDetailsController {
    private final EventService eventService;
    private final MessageService messageService;
    private final UserService userService;
    private final EntityManager entityManager;


    @Autowired
    public EventDetailsController(EventService eventService, MessageService messageService,
                                  UserService userService, EntityManager entityManager) {
        this.eventService = eventService;
        this.messageService = messageService;
        this.userService = userService;
        this.entityManager = entityManager;
    }

    @GetMapping("/eventDetails")
    public String getEventDetails(@RequestParam Long id, Model model) {
        EventDTO event = eventService.getEventById(id);
        List<MessageDTO> allMessages = messageService.findByEventId(event.getEventId());
        model.addAttribute("event", event);
        model.addAttribute("allMessages", allMessages);
        return "event-details";
    }

    @Transactional
    @PostMapping("/eventDetails")
    public String addComment(@RequestParam Long id, @RequestParam("comment") String commentText, Model model) {
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setText(commentText);
        messageDTO.setTimestamp(LocalDateTime.now());

        User user = userService.getUserById(1L).get();
        messageDTO.setSender(user);

        EventDTO eventById = eventService.getEventById(id);
        Event event = EventMapper.INSTANCE.toEvent(eventById);
        event = entityManager.merge(event);
        messageDTO.setEvent(event);

        messageService.createMessage(messageDTO);

        List<MessageDTO> allMessages = messageService.findByEventId(event.getEventId());

        model.addAttribute("allMessages", allMessages);
        model.addAttribute("event", eventById);

        return "event-details";
    }
}

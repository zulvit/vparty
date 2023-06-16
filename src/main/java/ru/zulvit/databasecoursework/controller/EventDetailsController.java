package ru.zulvit.databasecoursework.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.zulvit.databasecoursework.dto.EventDTO;
import ru.zulvit.databasecoursework.dto.MessageDTO;
import ru.zulvit.databasecoursework.dto.UserDTO;
import ru.zulvit.databasecoursework.mapper.EventMapper;
import ru.zulvit.databasecoursework.mapper.UserMapper;
import ru.zulvit.databasecoursework.service.EventService;
import ru.zulvit.databasecoursework.service.MessageService;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class EventDetailsController {
    private final EventService eventService;
    private final MessageService messageService;

    @Autowired
    public EventDetailsController(EventService eventService, MessageService messageService) {
        this.eventService = eventService;
        this.messageService = messageService;
    }

    @GetMapping("/eventDetails")
    public String getEventDetails(@RequestParam Long id, Model model) {
        EventDTO event = eventService.getEventById(id);
        List<MessageDTO> allMessages = messageService.findByEventId(event.getEventId());
        model.addAttribute("event", event);
        model.addAttribute("allMessages", allMessages);
        return "event-details";
    }

    @PostMapping("/eventDetails")
    public String addComment(@RequestParam Long id, @RequestParam("comment") String commentText, Model model) {
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setText(commentText);
        messageDTO.setTimestamp(LocalDateTime.now());
        UserDTO userDTO = new UserDTO();
        userDTO.setBirthday("2023-06-15T04:25");
        userDTO.setEmail("savva.kuplenik@yandex.ru");
        userDTO.setName("Savva");
        userDTO.setSurname("Kulenik");
        userDTO.setLastLoginDate("2023-06-15T04:25");
        userDTO.setPassword("sdfskdjfslk");
        userDTO.setRegistrationDate("2023-06-15T04:25");
        userDTO.setUserId(1);
        messageDTO.setSender(UserMapper.INSTANCE.toUser(userDTO));
        EventDTO event = eventService.getEventById(id);
        messageDTO.setEvent(EventMapper.INSTANCE.toEvent(event));
        messageService.createMessage(messageDTO);

        List<MessageDTO> allMessages = messageService.findByEventId(event.getEventId());

        model.addAttribute("allMessages", allMessages);
        model.addAttribute("event", eventService.getEventById(id));

        return "event-details";
    }

}

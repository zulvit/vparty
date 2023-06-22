package ru.zulvit.databasecoursework.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.zulvit.databasecoursework.dto.EventDTO;
import ru.zulvit.databasecoursework.mapper.EventMapper;
import ru.zulvit.databasecoursework.model.Invitation;
import ru.zulvit.databasecoursework.model.State;
import ru.zulvit.databasecoursework.model.User;
import ru.zulvit.databasecoursework.repository.StateRepository;
import ru.zulvit.databasecoursework.service.EventService;
import ru.zulvit.databasecoursework.service.InvitationService;
import ru.zulvit.databasecoursework.service.UserService;

import java.time.LocalDateTime;
import java.util.Optional;

@Controller
@RequestMapping("/invite")
public class InvitationController {
    private final UserService userService;
    private final InvitationService invitationService;
    private final EventService eventService;
    private final StateRepository stateRepository;

    @Autowired
    public InvitationController(UserService userService, InvitationService invitationService, EventService eventService, StateRepository stateRepository) {
        this.userService = userService;
        this.invitationService = invitationService;
        this.eventService = eventService;
        this.stateRepository = stateRepository;
    }

    @PostMapping("/send")
    public ModelAndView invite(@RequestParam String email, @RequestParam Long eventId) {
        User user = userService.findByEmail(email);

        if (user != null) {
            Invitation invitation = new Invitation();
            invitation.setSender(userService.getCurrentUser());
            invitation.setRecipient(user);
            invitation.setTime(LocalDateTime.now());
            EventDTO eventById = eventService.getEventById(eventId);
            invitation.setEvent(EventMapper.INSTANCE.toEvent(eventById));
            Optional<State> optionalState = stateRepository.findById(1L);
            invitation.setStatus(optionalState.get());

            invitationService.createInvitation(invitation);
            return new ModelAndView("redirect:/invite/success");
        } else {
            return new ModelAndView("redirect:/invite/error");
        }
    }

    @GetMapping("/success")
    public String success() {
        return "invite-success";
    }

    @GetMapping("/error")
    public String failed() {
        return "invite-failed";
    }
}

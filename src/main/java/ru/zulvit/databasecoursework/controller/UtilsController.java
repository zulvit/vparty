package ru.zulvit.databasecoursework.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.zulvit.databasecoursework.service.InvitationService;
import ru.zulvit.databasecoursework.service.MessageService;
import ru.zulvit.databasecoursework.service.OrganizerService;

@Controller
@RequestMapping("/utils/search")
public class UtilsController {
    private final InvitationService invitationService;
    private final MessageService messageService;
    private final OrganizerService organizerService;

    @Autowired
    public UtilsController(InvitationService invitationService, MessageService messageService, OrganizerService organizerService) {
        this.invitationService = invitationService;
        this.messageService = messageService;
        this.organizerService = organizerService;
    }

    @GetMapping
    public String utilPage() {
        return "utils";
    }

    @GetMapping("/invitations")
    public String searchInvitations(@RequestParam(required = false) Long eventId,
                                    @RequestParam(required = false) Integer userId,
                                    Model model) {
        if (eventId != null) {
            model.addAttribute("searchedInvitations", invitationService.findInvitationsByEventAndSender(eventId, userId));
        }
        return "utils";
    }

    @GetMapping("/acceptedUsers")
    public String searchAcceptedUsers(@RequestParam(required = false) Long eventId,
                                      Model model) {
        if (eventId != null) {
            model.addAttribute("acceptedUsers", invitationService.findAcceptedUsersByEvent(eventId));
        }
        return "utils";
    }

    @GetMapping("/messages")
    public String searchMessages(@RequestParam(required = false) Long eventId,
                                 Model model) {
        if (eventId != null) {
            model.addAttribute("messages", messageService.findMessagesByEvent(eventId));
        }
        return "utils";
    }

    @GetMapping("/organizers")
    public String searchOrganizers(@RequestParam(required = false) Long eventId,
                                   Model model) {
        if (eventId != null) {
            model.addAttribute("organizers", organizerService.findOrganizersByEvent(eventId));
        }
        return "utils";
    }
}
package ru.zulvit.databasecoursework.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.zulvit.databasecoursework.dto.InvitationDTO;
import ru.zulvit.databasecoursework.mapper.InvitationMapper;
import ru.zulvit.databasecoursework.model.Invitation;
import ru.zulvit.databasecoursework.model.User;
import ru.zulvit.databasecoursework.repository.InvitationRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class InvitationService {
    private final InvitationRepository invitationRepository;
    private final InvitationMapper invitationMapper = InvitationMapper.INSTANCE;

    @Autowired
    public InvitationService(InvitationRepository invitationRepository) {
        this.invitationRepository = invitationRepository;
    }

    public List<InvitationDTO> getAllInvitations() {
        List<Invitation> invitations = invitationRepository.findAll();
        List<InvitationDTO> invitationDTOS = new ArrayList<>();
        for (Invitation invitation : invitations) {
            invitationDTOS.add(InvitationMapper.INSTANCE.toInvitationDTO(invitation));
        }
        return invitationDTOS;
    }

    public InvitationDTO getInvitationById(Long invitationId) {
        Invitation invitation = invitationRepository.findById(invitationId)
                .orElseThrow(() -> new NoSuchElementException("No invitation found with id: " + invitationId));
        return InvitationMapper.INSTANCE.toInvitationDTO(invitation);
    }

    public void createInvitation(Invitation invitation) {
        invitationRepository.save(invitation);
    }

    public void deleteInvitation(Long invitationId) {
        invitationRepository.deleteById(invitationId);
    }

    public List<Invitation> findInvitationsByEventAndSender(Long eventId, Integer userId) {
        return invitationRepository.findInvitationsByEventAndSender(eventId, userId);
    }

    public List<User> findAcceptedUsersByEvent(Long eventId) {
        return invitationRepository.findAcceptedUsersByEvent(eventId);
    }
}

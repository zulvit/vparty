package ru.zulvit.databasecoursework.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.zulvit.databasecoursework.model.Organizer;
import ru.zulvit.databasecoursework.repository.OrganizerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class OrganizerService {

    private final OrganizerRepository organizerRepository;

    @Autowired
    public OrganizerService(OrganizerRepository organizerRepository) {
        this.organizerRepository = organizerRepository;
    }

    public Organizer createOrganizer(Organizer organizer) {
        return organizerRepository.save(organizer);
    }

    public Optional<Organizer> getOrganizer(Long organizerId) {
        return organizerRepository.findById(organizerId);
    }

    public List<Organizer> getAllOrganizers() {
        return organizerRepository.findAll();
    }

    public void deleteOrganizer(Long organizerId) {
        organizerRepository.deleteById(organizerId);
    }

    public List<Organizer> findOrganizersByEvent(Long eventId) {
        return organizerRepository.findOrganizersByEvent(eventId);
    }
}

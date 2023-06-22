package ru.zulvit.databasecoursework.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.zulvit.databasecoursework.dto.EventDTO;
import ru.zulvit.databasecoursework.mapper.EventMapper;
import ru.zulvit.databasecoursework.model.*;
import ru.zulvit.databasecoursework.repository.EventRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class EventService {
    private final EventRepository eventRepository;
    private final EventMapper eventMapper = EventMapper.INSTANCE;


    @Autowired
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<EventDTO> getAllSortedEvents(Sort sort) {
        return eventRepository.findAll(sort).stream()
                .map(eventMapper::toEventDTO)
                .collect(Collectors.toList());
    }

    public List<EventDTO> getAllEvents() {
        List<Event> events = eventRepository.findAll();
        List<EventDTO> eventDTOS = new ArrayList<>();
        for (Event event : events) {
            eventDTOS.add(EventMapper.INSTANCE.toEventDTO(event));
        }
        return eventDTOS;
    }

    public EventDTO getEventById(Long eventId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new NoSuchElementException("No event found with id: " + eventId));
        return EventMapper.INSTANCE.toEventDTO(event);
    }

    public void createEvent(EventDTO eventDTO) {
        Event event = EventMapper.INSTANCE.toEvent(eventDTO);
        eventRepository.save(event);
    }

    public void deleteEvent(Long eventId) {
        eventRepository.deleteById(eventId);
    }

    public List<Event> searchEvents(String term) {
        return eventRepository.findByTitleContaining(term);
    }

}

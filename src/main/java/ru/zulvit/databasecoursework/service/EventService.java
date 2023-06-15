package ru.zulvit.databasecoursework.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.zulvit.databasecoursework.dto.EventDTO;
import ru.zulvit.databasecoursework.mapper.EventMapper;
import ru.zulvit.databasecoursework.model.Event;
import ru.zulvit.databasecoursework.repository.EventRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class EventService {

    private final EventRepository eventRepository;

    @Autowired
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
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
        System.out.println(event);
        eventRepository.save(event);
    }
}

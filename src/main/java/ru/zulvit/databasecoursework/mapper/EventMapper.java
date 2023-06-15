package ru.zulvit.databasecoursework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.zulvit.databasecoursework.dto.EventDTO;
import ru.zulvit.databasecoursework.model.Event;

@Mapper
public interface EventMapper {
    EventMapper INSTANCE = Mappers.getMapper(EventMapper.class);

    EventDTO toEventDTO(Event event);

    Event toEvent(EventDTO eventDTO);
}

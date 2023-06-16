package ru.zulvit.databasecoursework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.zulvit.databasecoursework.dto.MessageDTO;
import ru.zulvit.databasecoursework.model.Message;

@Mapper
public interface MessageMapper {
    MessageMapper INSTANCE = Mappers.getMapper(MessageMapper.class);

    MessageDTO toMessageDTO(Message message);

    Message toMessage(MessageDTO messageDto);
}

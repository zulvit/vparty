package ru.zulvit.databasecoursework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.zulvit.databasecoursework.dto.UserDTO;
import ru.zulvit.databasecoursework.model.User;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDTO toUserDTO(User user);

    User toUser(UserDTO userDTO);
}

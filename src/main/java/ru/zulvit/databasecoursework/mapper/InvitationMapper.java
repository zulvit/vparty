package ru.zulvit.databasecoursework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.zulvit.databasecoursework.dto.InvitationDTO;
import ru.zulvit.databasecoursework.model.Invitation;

@Mapper
public interface InvitationMapper {
    InvitationMapper INSTANCE = Mappers.getMapper(InvitationMapper.class);

    InvitationDTO toInvitationDTO(Invitation invitation);

    Invitation toInvitation(InvitationDTO invitationDTO);
}

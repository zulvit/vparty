package ru.zulvit.databasecoursework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.zulvit.databasecoursework.model.Invitation;
import ru.zulvit.databasecoursework.model.User;

import java.util.List;

@Repository
public interface InvitationRepository extends JpaRepository<Invitation, Long> {
    @Query("SELECT i FROM Invitation i JOIN i.event e JOIN i.sender s WHERE e.eventId = :eventId AND s.userId = :userId")
    List<Invitation> findInvitationsByEventAndSender(@Param("eventId") Long eventId, @Param("userId") Integer userId);

    @Query("SELECT i.recipient FROM Invitation i JOIN i.event e JOIN i.status s WHERE e.eventId = :eventId AND s.description = 'ACCEPTED'")
    List<User> findAcceptedUsersByEvent(@Param("eventId") Long eventId);
}
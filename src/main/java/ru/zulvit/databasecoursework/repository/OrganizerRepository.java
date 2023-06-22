package ru.zulvit.databasecoursework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.zulvit.databasecoursework.model.Organizer;

import java.util.List;

@Repository
public interface OrganizerRepository extends JpaRepository<Organizer, Long> {
    @Query("SELECT o FROM Organizer o JOIN o.event e WHERE e.eventId = :eventId")
    List<Organizer> findOrganizersByEvent(@Param("eventId") Long eventId);
}

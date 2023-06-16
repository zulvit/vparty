package ru.zulvit.databasecoursework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.zulvit.databasecoursework.model.Organizer;

@Repository
public interface OrganizerRepository extends JpaRepository<Organizer, Long> {
}

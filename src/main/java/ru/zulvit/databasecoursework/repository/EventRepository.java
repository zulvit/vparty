package ru.zulvit.databasecoursework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.zulvit.databasecoursework.model.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
}

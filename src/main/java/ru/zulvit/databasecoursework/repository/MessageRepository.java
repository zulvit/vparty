package ru.zulvit.databasecoursework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.zulvit.databasecoursework.model.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {
}
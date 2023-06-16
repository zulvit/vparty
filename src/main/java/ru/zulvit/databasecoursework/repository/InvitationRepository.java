package ru.zulvit.databasecoursework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.zulvit.databasecoursework.model.Invitation;

@Repository
public interface InvitationRepository extends JpaRepository<Invitation, Long> {
}
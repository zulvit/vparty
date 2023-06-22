package ru.zulvit.databasecoursework.dto;

import jakarta.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;
import ru.zulvit.databasecoursework.model.Event;
import ru.zulvit.databasecoursework.model.User;

import java.time.LocalDateTime;

public class MessageDTO {

    private Long messageId;

    @NotEmpty(message = "Event is required")
    private Event event;

    @NotEmpty(message = "Sender is required")
    private User sender;

    @NotEmpty(message = "Text is required")
    @Length(max = 1000, message = "Text should be less than 1000 characters")
    private String text;

    private LocalDateTime timestamp;

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "MessageDto{" +
                "messageId=" + messageId +
                ", event=" + event +
                ", sender=" + sender +
                ", text='" + text + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}

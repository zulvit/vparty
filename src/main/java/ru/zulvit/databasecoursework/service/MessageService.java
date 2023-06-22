package ru.zulvit.databasecoursework.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.zulvit.databasecoursework.dto.MessageDTO;
import ru.zulvit.databasecoursework.mapper.MessageMapper;
import ru.zulvit.databasecoursework.model.Message;
import ru.zulvit.databasecoursework.repository.MessageRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class MessageService {

    private final MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public List<MessageDTO> getAllMessages() {
        List<Message> messages = messageRepository.findAll();
        List<MessageDTO> messageDTOS = new ArrayList<>();
        for (Message message : messages) {
            messageDTOS.add(MessageMapper.INSTANCE.toMessageDTO(message));
        }
        return messageDTOS;
    }

    public MessageDTO getMessageById(Long messageId) {
        Message message = messageRepository.findById(messageId)
                .orElseThrow(() -> new NoSuchElementException("No message found with id: " + messageId));
        return MessageMapper.INSTANCE.toMessageDTO(message);
    }

    public List<MessageDTO> findByEventId(Long eventId) {
        List<Message> messages = messageRepository.findByEventId(eventId);
        List<MessageDTO> messageDTOS = new ArrayList<>();
        for (Message message : messages) {
            messageDTOS.add(MessageMapper.INSTANCE.toMessageDTO(message));
        }
        return messageDTOS;
    }

    public void createMessage(MessageDTO messageDTO) {
        Message message = MessageMapper.INSTANCE.toMessage(messageDTO);
        System.out.println(message);
        messageRepository.save(message);
    }

    public List<Message> findMessagesByEvent(Long eventId) {
        return messageRepository.findMessagesByEvent(eventId);
    }
}

package com.chat.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.chat.model.Message;
import com.chat.repository.Messagerepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MessageService {
    
    private final Messagerepo messageRepository;

    public ResponseEntity<Message> getMessage(UUID id) {
        Optional<Message> optionalMessage = messageRepository.findById(id);
        if (optionalMessage.isPresent())
            return ResponseEntity.ok(optionalMessage.get());
        return ResponseEntity.notFound().build();
    }



}

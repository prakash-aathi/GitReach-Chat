package com.chat.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.chat.model.Message;
import com.chat.model.MessageList;
import com.chat.model.MessageListPrimaryKey;
import com.chat.repository.MessageListRepo;
import com.chat.repository.Messagerepo;
import com.chat.repository.UnreadMessageRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MessageService {
    
    private final Messagerepo messageRepository;
    private final UnreadMessageRepo unreadMessageRepo;
    private final MessageListRepo messageListRepo;

    public ResponseEntity<Message> getMessage(UUID id,String userName) {
        Optional<Message> optionalMessage = messageRepository.findById(id);
        
        // find msg list particular message and get unread if true then decrement and chnage to false
        if (optionalMessage.isPresent()) {

            // building PK for message list
            MessageListPrimaryKey messageListPrimaryKey = MessageListPrimaryKey.builder()
                    .userId(userName)
                    .label("INBOX")
                    .timeId(id)
                    .build();
            Optional<MessageList> optionalMessageListItem = messageListRepo.findById(messageListPrimaryKey);
            if (optionalMessageListItem.isPresent()) {
                MessageList messageList = optionalMessageListItem.get();
                if (!messageList.isRead()) {
                    messageList.setRead(true);
                    messageListRepo.save(messageList);
                    unreadMessageRepo.decrementUnreadCounter(userName, "INBOX"); 
                }
            }
            return ResponseEntity.ok(optionalMessage.get());   
        }
        return ResponseEntity.notFound().build();
    }



}

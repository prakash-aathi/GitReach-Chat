package com.chat.service;

import java.util.Date;
import java.util.List;

import org.ocpsoft.prettytime.PrettyTime;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.chat.model.MessageList;
import com.chat.repository.MessageListRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MessageListService {

    private final MessageListRepo messageListRepo;

    public ResponseEntity<List<MessageList>> getAllMessages(String name) {

        List<MessageList> messageList = messageListRepo.findAllById_UserIdAndId_Label(name, "INBOX");
        PrettyTime prettyTime = new PrettyTime();
        messageList.stream().forEach(message -> {
            Date messageDate = new Date(message.getId().getTimeId().timestamp());
            message.setAgoTimeString(prettyTime.format(messageDate));
        });
        return ResponseEntity.ok(messageList);
    }

}

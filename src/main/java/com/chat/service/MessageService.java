package com.chat.service;

import java.util.Date;
import java.util.List;

import org.ocpsoft.prettytime.PrettyTime;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.chat.model.MessageList;
import com.chat.model.MessageListPrimaryKey;
import com.chat.repository.MessageListRepo;
import com.datastax.oss.driver.api.core.uuid.Uuids;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MessageService {

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

    public ResponseEntity<String> saveMessage(String name) {
        MessageListPrimaryKey primaryKey = new MessageListPrimaryKey();
        primaryKey.setUserId(name);
        primaryKey.setLabel("INBOX");
        primaryKey.setTimeId(Uuids.timeBased());

        MessageList messageList = new MessageList();
        messageList.setId(primaryKey);
        messageList.setFrom(name);
        messageList.setTo(List.of(name));
        messageList.setRead(false);
        messageList.setSubject("Test Subject");

        return ResponseEntity.ok(messageListRepo.save(messageList).toString());
    }

}

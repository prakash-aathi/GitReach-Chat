package com.chat.service;

import java.util.Date;
import java.util.List;

import org.ocpsoft.prettytime.PrettyTime;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.chat.model.Message;
import com.chat.model.MessageList;
import com.chat.model.MessageListPrimaryKey;
import com.chat.repository.MessageListRepo;
import com.chat.repository.Messagerepo;
import com.datastax.oss.driver.api.core.uuid.Uuids;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MessageListService {

    private final MessageListRepo messageListRepo;
    private final Messagerepo messagerepo;

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

        Message message = new Message();
        message.setId(Uuids.timeBased());
        message.setFrom(name);
        message.setTo(List.of(name));
        message.setBody("Test Body");
        message.setSubject("Test Subject");

        System.out.println(messagerepo.save(message));

        return ResponseEntity.ok(messageListRepo.save(messageList).toString());
    }

}

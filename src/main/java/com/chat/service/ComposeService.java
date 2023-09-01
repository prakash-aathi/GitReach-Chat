package com.chat.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.chat.dto.MessageRequest;
import com.chat.model.Message;
import com.chat.model.MessageList;
import com.chat.model.MessageListPrimaryKey;
import com.chat.repository.MessageListRepo;
import com.chat.repository.Messagerepo;
import com.datastax.oss.driver.api.core.uuid.Uuids;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ComposeService {

        private final Messagerepo messagerepo;
        private final MessageListRepo messageListRepo;

        public ResponseEntity<String> send(MessageRequest message) {

                // prepare To list
                List<String> to = Arrays.asList(message.getToIds().split(",")).stream()
                                .map(StringUtils::trimWhitespace)
                                .filter(StringUtils::hasText)
                                .distinct()
                                .collect(Collectors.toList());

                // Save message to database
                Message prepareMessage = Message.builder()
                                .body(message.getMessage())
                                .from(message.getFrom())
                                .subject(message.getSubject())
                                .id(Uuids.timeBased())
                                .to(to)
                                .build();

                messagerepo.save(prepareMessage);

                // Save message to folder for each recipient
                to.stream().forEach(toId -> {
                        MessageList messageList = convertToMessageList(message, to, prepareMessage, toId, "INBOX");

                        messageListRepo.save(messageList);

                });

                // Save message to sender's sent folder
                MessageList messageList = convertToMessageList(message, to, prepareMessage, message.getFrom(), "SENT");
                messageListRepo.save(messageList);

                return ResponseEntity.ok("Message sent successfully");
        }

        private MessageList convertToMessageList(MessageRequest message, List<String> to, Message prepareMessage,
                        String toId, String label) {
                MessageListPrimaryKey primaryKey = MessageListPrimaryKey.builder()
                                .userId(toId)
                                .label(label)
                                .timeId(prepareMessage.getId())
                                .build();

                MessageList messageList = MessageList.builder()
                                .id(primaryKey)
                                .from(message.getFrom())
                                .subject(message.getSubject())
                                .to(to)
                                .isRead(false)
                                .build();
                return messageList;
        }

}

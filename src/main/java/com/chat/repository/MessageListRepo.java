package com.chat.repository;

import java.util.List;

import org.springframework.data.cassandra.repository.CassandraRepository;

import com.chat.model.MessageList;
import com.chat.model.MessageListPrimaryKey;

public interface MessageListRepo extends CassandraRepository<MessageList, MessageListPrimaryKey> {

    List<MessageList> findAllById_UserIdAndId_Label(String userId, String label);

}

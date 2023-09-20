package com.chat.repository;

import java.util.List;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;

import com.chat.model.UnreadMessage;

public interface UnreadMessageRepo extends CassandraRepository<UnreadMessage, String> {
    
    List<UnreadMessage> findAllById(String id);

    @Query("update unread_message set unreadcount = unreadcount + 1 where user_id = ?0 and label = ?1")
    void incrementUnreadCounter(String id, String label);
    @Query("update unread_message set unreadcount = unreadcount - 1 where user_id = ?0 and label = ?1")
    void decrementUnreadCounter(String id, String label);
}

package com.chat.repository;

import java.util.UUID;

import org.springframework.data.cassandra.repository.CassandraRepository;

import com.chat.model.Message;

public interface Messagerepo extends CassandraRepository<Message, UUID> {
    
}

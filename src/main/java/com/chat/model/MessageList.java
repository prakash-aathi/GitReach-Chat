package com.chat.model;

import java.util.List;

import org.springframework.data.annotation.Transient;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.CassandraType.Name;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import lombok.Builder;
import lombok.Data;

@Table(value = "message_list")
@Data
@Builder
public class MessageList {

    @PrimaryKey
    private MessageListPrimaryKey id;

    @CassandraType(type = CassandraType.Name.TEXT)
    private String from;

    @CassandraType(type = CassandraType.Name.LIST, typeArguments = Name.TEXT)
    private List<String> to;

    @CassandraType(type = CassandraType.Name.TEXT)
    private String subject;

    @CassandraType(type = CassandraType.Name.BOOLEAN)
    private boolean isRead;

    @Transient
    private String agoTimeString;

    public MessageList() {
    }
    
    public MessageList(MessageListPrimaryKey id, String from, List<String> to, String subject, boolean isRead,
            String agoTimeString) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.subject = subject;
        this.isRead = isRead;
        this.agoTimeString = agoTimeString;
    }

    public MessageList(MessageListPrimaryKey id, String from, List<String> to, String subject, boolean isRead) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.subject = subject;
        this.isRead = isRead;
    }

    
}

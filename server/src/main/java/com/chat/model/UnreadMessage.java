package com.chat.model;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;
import org.springframework.data.cassandra.core.mapping.CassandraType.Name;

import lombok.Builder;
import lombok.Data;

@Table(value = "unread_message")
@Data
@Builder
public class UnreadMessage {

    @PrimaryKeyColumn(name = "user_id", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private String id;

    @PrimaryKeyColumn(name="label",ordinal = 1,type = PrimaryKeyType.CLUSTERED)
    private String label;

    @CassandraType(type = Name.COUNTER)
    private String unreadCount;
}

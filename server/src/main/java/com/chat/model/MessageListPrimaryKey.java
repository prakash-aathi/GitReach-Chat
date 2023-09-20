package com.chat.model;

import java.util.UUID;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

import lombok.Builder;
import lombok.Data;

@PrimaryKeyClass
@Data
@Builder
public class MessageListPrimaryKey {
    
    @PrimaryKeyColumn(name = "user_id", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private String userId;

    @PrimaryKeyColumn(name = "label", ordinal = 1, type = PrimaryKeyType.PARTITIONED)
    private String label;

    @PrimaryKeyColumn(name = "create_time_uuid", ordinal = 2, type = PrimaryKeyType.CLUSTERED)
    @CassandraType(type = CassandraType.Name.TIMEUUID)
    private UUID timeId;
    

}

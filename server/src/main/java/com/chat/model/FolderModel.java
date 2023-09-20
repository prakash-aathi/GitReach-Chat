package com.chat.model;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;

@Table("folder_by_user")
@Data
@AllArgsConstructor
public class FolderModel {

    @PrimaryKeyColumn(name="user_id",ordinal = 0,type = PrimaryKeyType.PARTITIONED)
    private String id;

    @PrimaryKeyColumn(name="label",ordinal = 1,type = PrimaryKeyType.CLUSTERED)
    private String label;

    @CassandraType(type = CassandraType.Name.TEXT)
    private String color;

}

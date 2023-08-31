package com.chat.repository;

import java.util.List;

import org.springframework.data.cassandra.repository.CassandraRepository;

import com.chat.model.FolderModel;

public interface FolderRepo extends CassandraRepository<FolderModel, String> {
    
    List<FolderModel> findAllById(String id);
}

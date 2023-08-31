package com.chat.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.chat.model.FolderModel;
import com.chat.repository.FolderRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FolderService {

    private final FolderRepo folderRepo;

    public ResponseEntity<List<FolderModel>> getAllFolders(String name) {
        return ResponseEntity.ok().body(folderRepo.findAllById(name));
    }

    public ResponseEntity<String> saveFolder(String name) {
        folderRepo.save(new FolderModel("prakash-aathi","inbox","blue"));
        return ResponseEntity.created(null).body("created");
    }

}

package com.chat.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.chat.dto.FolderResponse;
import com.chat.model.FolderModel;
import com.chat.model.UnreadMessage;
import com.chat.repository.FolderRepo;
import com.chat.repository.UnreadMessageRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FolderService {

    private final FolderRepo folderRepo;
    private final UnreadMessageRepo unreadMessageRepo;

    public ResponseEntity<List<FolderResponse>> getAllFolders(String name) {
        List<FolderModel> folderDetails = folderRepo.findAllById(name);
        List<UnreadMessage> unreadMessage = unreadMessageRepo.findAllById(name);
        List<FolderResponse> folderResponse = folderDetails.stream().map(folder -> {
            return FolderResponse.builder()
                    .id(folder.getId())
                    .label(folder.getLabel())
                    .color(folder.getColor())
                    .unreadCount(
                            unreadMessage.stream()
                            .filter(unread -> unread.getLabel().equals(folder.getLabel()))
                            .findFirst()
                            .map(UnreadMessage::getUnreadCount)
                            .orElse("0")
                    )
                    .build();
        }).collect(Collectors.toList());

        return ResponseEntity.ok(folderResponse);
    }

    public ResponseEntity<String> saveFolder(String name) {
        folderRepo.save(new FolderModel("prakash-aathi","inbox","blue"));
        return ResponseEntity.created(null).body("created");
    }

}

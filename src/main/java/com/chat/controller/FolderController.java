package com.chat.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chat.model.FolderModel;
import com.chat.service.FolderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/folders")
@RequiredArgsConstructor
public class FolderController {

    private final FolderService folderService;
    
    @GetMapping()
    public ResponseEntity<List<FolderModel>> getAllFolders(@AuthenticationPrincipal OAuth2User principal) {

        String name = principal.getAttribute("login");

        if (principal == null || name == null)
            return ResponseEntity.badRequest().build();

        return folderService.getAllFolders(name);

    }
    
    @GetMapping("/save")
    public ResponseEntity<String> saveFolder(@AuthenticationPrincipal OAuth2User principal) {

        String name = principal.getAttribute("login");

        if (principal == null || name == null)
            return ResponseEntity.badRequest().build();

        return folderService.saveFolder(name);

    }
}

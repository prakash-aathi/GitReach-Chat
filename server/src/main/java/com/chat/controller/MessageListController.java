package com.chat.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chat.model.MessageList;
import com.chat.service.MessageListService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/messages")
@RequiredArgsConstructor
public class MessageListController {
    
    private final MessageListService messageService;

    @GetMapping()
    public ResponseEntity<List<MessageList>> getAllMessages(@AuthenticationPrincipal OAuth2User principal) {

        String name = principal.getAttribute("login");

        if (principal == null || name == null)
            return ResponseEntity.badRequest().build();

        return messageService.getAllMessages(name);

    }


}

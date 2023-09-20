package com.chat.controller;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chat.model.Message;
import com.chat.service.MessageService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/message")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @GetMapping("{id}")
    public ResponseEntity<Message> getMessage(@PathVariable UUID id, @AuthenticationPrincipal OAuth2User principal) {
        if (principal == null || principal.getAttribute("login") == null)
            return ResponseEntity.badRequest().build();
        return messageService.getMessage(id,principal.getAttribute("login"));
    }

}

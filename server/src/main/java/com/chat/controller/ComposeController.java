package com.chat.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chat.dto.MessageRequest;
import com.chat.service.ComposeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/compose")
@RequiredArgsConstructor
public class ComposeController {

    private final ComposeService composeService;

    @PostMapping
    public ResponseEntity<String> send(@AuthenticationPrincipal OAuth2User principal,
            @RequestBody MessageRequest message) {
        String name = principal.getAttribute("login");

        if (principal == null || name == null)
            return ResponseEntity.badRequest().build();
        message.setFrom(name);
        return composeService.send(message);
    }

}

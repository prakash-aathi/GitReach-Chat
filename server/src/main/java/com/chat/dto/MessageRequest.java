package com.chat.dto;

import lombok.Data;

@Data
public class MessageRequest {
    
    private String message;
    private String toIds;
    private String from;
    private String subject;
    
}

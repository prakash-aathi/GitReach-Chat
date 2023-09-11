package com.chat.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FolderResponse {
    
    private String id;

    private String label;

    private String color;

    private String unreadCount;
}

package com.mursalin.chat_app.dto;

import com.mursalin.chat_app.model.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserStatusEvent {
    private String userId;
    private Status status;
}

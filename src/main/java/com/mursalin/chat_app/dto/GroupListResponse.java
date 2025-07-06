package com.mursalin.chat_app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class GroupListResponse {

    private String groupName;
    private List<String> memberList = new ArrayList<>();
}

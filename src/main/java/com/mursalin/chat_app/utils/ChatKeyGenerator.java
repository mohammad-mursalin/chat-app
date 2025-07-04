package com.mursalin.chat_app.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ChatKeyGenerator {
    public static String generate(List<String> membersId) {
        List<String> sorted = new ArrayList<>(membersId);
        Collections.sort(sorted);
        return String.join("_", sorted);
    }

}

package com.mursalin.chat_app.utils;

import com.mursalin.chat_app.dto.UserStatusEvent;
import com.mursalin.chat_app.model.Status;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Getter
@RequiredArgsConstructor
public class PresenceEventListener {

    private final Set<String> onlineUsers = ConcurrentHashMap.newKeySet();
    private final Map<String, String> sessionIdToUserId = new ConcurrentHashMap<>();
    private final SimpMessagingTemplate messagingTemplate;

    @EventListener
    public void handleSessionConnect(SessionConnectEvent event) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(event.getMessage());
        String userId = accessor.getFirstNativeHeader("userId");
        String sessionId = accessor.getSessionId();

        if (userId != null && sessionId != null) {
            onlineUsers.add(userId);
            sessionIdToUserId.put(sessionId, userId);
            messagingTemplate.convertAndSend("/user-status", new UserStatusEvent(userId, Status.ONLINE));
        }
    }

    @EventListener
    public void handleSessionDisconnect(SessionDisconnectEvent event) {
        String sessionId = StompHeaderAccessor.wrap(event.getMessage()).getSessionId();
        if (sessionId != null) {
            String userId = sessionIdToUserId.remove(sessionId);
            if (userId != null) {
                onlineUsers.remove(userId);
                messagingTemplate.convertAndSend("/user-status", new UserStatusEvent(userId, Status.OFFLINE));
            }
        }
    }
}


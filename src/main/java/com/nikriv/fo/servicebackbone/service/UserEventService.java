package com.nikriv.fo.servicebackbone.service;

import com.nikriv.fo.servicebackbone.dto.UserDto;
import com.nikriv.fo.servicebackbone.event.UserEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserEventService {
    
    private final StreamBridge streamBridge;
    
    public void publishUserCreated(UserDto user) {
        UserEvent event = createUserEvent("USER_CREATED", user);
        publishEvent(event);
        log.info("Published USER_CREATED event for user: {}", user.getUsername());
    }
    
    public void publishUserUpdated(UserDto user) {
        UserEvent event = createUserEvent("USER_UPDATED", user);
        publishEvent(event);
        log.info("Published USER_UPDATED event for user: {}", user.getUsername());
    }
    
    public void publishUserDeleted(UserDto user) {
        UserEvent event = createUserEvent("USER_DELETED", user);
        publishEvent(event);
        log.info("Published USER_DELETED event for user: {}", user.getUsername());
    }
    
    private UserEvent createUserEvent(String eventType, UserDto user) {
        return UserEvent.builder()
                .eventType(eventType)
                .userId(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .timestamp(LocalDateTime.now())
                .build();
    }
    
    private void publishEvent(UserEvent event) {
        streamBridge.send("user-events", event);
    }
}
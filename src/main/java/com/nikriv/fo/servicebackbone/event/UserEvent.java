package com.nikriv.fo.servicebackbone.event;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEvent {
    private String eventType;
    private Long userId;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private LocalDateTime timestamp;
}
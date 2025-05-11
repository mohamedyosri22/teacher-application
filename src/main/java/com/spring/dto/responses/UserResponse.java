package com.spring.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {

    private long id;
    private String name;
    private String email;
    private String picture;
    private String role;
    private String studentLevel;
    private boolean tookLevelTest;
    private LocalDateTime joinedAt;
    private Set<Long> watchedVideos;
}

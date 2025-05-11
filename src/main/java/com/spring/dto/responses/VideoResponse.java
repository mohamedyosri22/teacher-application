package com.spring.dto.responses;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record VideoResponse(
        long id,
        String url,
        String lvl,
        LocalDateTime addedAt
) {
}

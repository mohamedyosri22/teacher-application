package com.spring.dto.responses;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record QuestionResponse(
        long id,
        String title,
        String answer,
        String lvlName,
        LocalDateTime addedAt
) {
}

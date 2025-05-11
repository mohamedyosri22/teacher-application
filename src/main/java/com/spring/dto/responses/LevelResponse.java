package com.spring.dto.responses;

import lombok.Builder;

@Builder
public record LevelResponse(
        int id,
        String lvlName
) {
}

package com.spring.dto.requests;

import lombok.Builder;

@Builder
public record LevelRequest(
        String lvlName
) {
}

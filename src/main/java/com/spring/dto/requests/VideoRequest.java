package com.spring.dto.requests;

import com.spring.model.Level;
import lombok.Builder;

@Builder
public record VideoRequest(
        String url,
        String lvlName
) {
}

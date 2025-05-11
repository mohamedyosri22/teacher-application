package com.spring.dto.requests;

import lombok.Builder;

@Builder
public record QuestionRequest (
        String title,
        String answer,
        String lvlName
){
}

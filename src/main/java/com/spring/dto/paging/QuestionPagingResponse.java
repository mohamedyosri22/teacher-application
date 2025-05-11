package com.spring.dto.paging;

import com.spring.dto.responses.QuestionResponse;
import lombok.Builder;

import java.util.List;

@Builder
public record QuestionPagingResponse(
        List<QuestionResponse> questions,
        long totalElements,
        int totalPages,
        int pageNo,
        int pageSize,
        boolean isLast
) {
}

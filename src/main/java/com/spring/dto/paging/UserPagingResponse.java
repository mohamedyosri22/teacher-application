package com.spring.dto.paging;

import com.spring.dto.responses.UserResponse;
import lombok.Builder;

import java.util.List;

@Builder
public record UserPagingResponse(
        List<UserResponse> data,
        long totalElements,
        int totalPages,
        int pageNo,
        int pageSize,
        boolean isLast
) {
}

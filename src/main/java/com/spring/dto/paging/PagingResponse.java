package com.spring.dto.paging;

import com.spring.dto.responses.VideoResponse;
import lombok.Builder;

import java.util.List;

@Builder
public record PagingResponse(
        List<VideoResponse> videos,
        long totalElements,
        int pageNo,
        int pageSize,
        int totalPages,
        boolean isLast
) {

}

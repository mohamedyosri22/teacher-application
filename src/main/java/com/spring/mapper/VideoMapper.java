package com.spring.mapper;

import com.spring.dto.requests.VideoRequest;
import com.spring.dto.responses.VideoResponse;
import com.spring.model.Video;
import com.spring.repository.LevelRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class VideoMapper {
    private final LevelRepo levelRepo;

    public Video toVideo(VideoRequest request){
        var lvl = levelRepo.findByName(request.lvlName())
                .orElseThrow(()-> new EntityNotFoundException("Level not found"));

        return Video.builder()
                .url(request.url())
                .videoLvl(lvl)
                .addedAt(LocalDateTime.now())
                .build();
    }

    public VideoResponse toVideoResponse(Video video){
        return VideoResponse.builder()
                .id(video.getId())
                .lvl(video.getVideoLvl().getName())
                .url(video.getUrl())
                .addedAt(video.getAddedAt())
                .build();
    }
}

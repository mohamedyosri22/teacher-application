package com.spring.service;

import com.spring.dto.requests.VideoRequest;
import com.spring.dto.paging.PagingResponse;
import com.spring.dto.responses.VideoResponse;
import com.spring.mapper.VideoMapper;
import com.spring.model.Video;
import com.spring.repository.LevelRepo;
import com.spring.repository.VideoRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VideoService {
    private final VideoRepo videoRepo;
    private final LevelRepo levelRepo;
    private final VideoMapper mapper;

    public VideoResponse addVideo(VideoRequest request){
        Video video = mapper.toVideo(request);

        return mapper.toVideoResponse(videoRepo.save(video));
    }

    public void deleteVideo(long id){
        Video video = videoRepo.findById(id)
                .orElseThrow(()->new EntityNotFoundException("Video not found"));
        videoRepo.delete(video);
    }

    public VideoResponse getById(long id){
        return mapper.toVideoResponse(videoRepo.findById(id)
                .orElseThrow(()->new EntityNotFoundException("Video not found")));
    }

    public List<VideoResponse> getByLvl(String lvl){
        var level = levelRepo.findByName(lvl)
                .orElseThrow(()->new EntityNotFoundException("Level not found"));
        return videoRepo.findByVideoLvlNameOrderByAddedAt(lvl)
                .stream()
                .map(mapper::toVideoResponse)
                .toList();
    }

    public PagingResponse getAllVideos(int pageNo,int pageSize){
        Pageable pageable = PageRequest.of(pageNo,pageSize,
                Sort.by("addedAt").descending());

        Page<Video> videos = videoRepo.findAll(pageable);

        List<VideoResponse> videoResponses = videos
                .getContent()
                .stream()
                .map(mapper::toVideoResponse)
                .toList();

        return PagingResponse.builder()
                .videos(videoResponses)
                .pageNo(videos.getNumber())
                .isLast(videos.isLast())
                .pageSize(videos.getSize())
                .totalElements(videos.getTotalElements())
                .totalPages(videos.getTotalPages())
                .build();
    }


    public VideoResponse updateVideoData(long id, VideoRequest request) {
        Video video = videoRepo.findById(id)
                .orElseThrow(()->new EntityNotFoundException("Video doesn't exist"));

        var lvl = levelRepo.findByName(request.lvlName())
                .orElseThrow(()-> new EntityNotFoundException("Level not found"));

        video.setVideoLvl(lvl);
        video.setUrl(request.url());

        return mapper.toVideoResponse(videoRepo.save(video));
    }
}

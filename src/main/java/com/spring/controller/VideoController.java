package com.spring.controller;

import com.spring.dto.requests.VideoRequest;
import com.spring.dto.paging.PagingResponse;
import com.spring.dto.responses.VideoResponse;
import com.spring.service.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/video")
@RequiredArgsConstructor
public class VideoController {
    private final VideoService videoService;

    @PostMapping
    public ResponseEntity<VideoResponse> addVideo(@RequestBody VideoRequest request) {
        return new ResponseEntity<>(videoService.addVideo(request), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<PagingResponse> getAllVideos(@RequestParam(name = "pageNo", required = false, defaultValue = "0") int pageNo
            , @RequestParam(name = "pageSize", required = false, defaultValue = "20") int pageSize) {
        return ResponseEntity.ok(videoService.getAllVideos(pageNo, pageSize));
    }

    @GetMapping("/by-id/{id}")
    public ResponseEntity<VideoResponse> getById(@PathVariable long id) {
        return ResponseEntity.ok(videoService.getById(id));
    }

    @GetMapping("/by-lvlName/{lvlName}")
    public ResponseEntity<List<VideoResponse>> getByLvl(@PathVariable String lvlName) {
        return ResponseEntity.ok(videoService.getByLvl(lvlName));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteById(@RequestParam("id")long id){
        videoService.deleteVideo(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<VideoResponse> updateVideoData(@PathVariable long id,@RequestBody VideoRequest request){
        return ResponseEntity.ok(videoService.updateVideoData(id,request));
    }

}
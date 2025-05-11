package com.spring.mapper;

import com.spring.dto.requests.UserRequest;
import com.spring.dto.responses.UserResponse;
import com.spring.model.User;
import com.spring.model.Video;
import com.spring.repository.LevelRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserMapper {
    private final LevelRepo levelRepo;

    public User toUser(UserRequest request){
        var lvl = levelRepo.findByName("PRE")
                .orElseThrow(()-> new EntityNotFoundException("Level not found"));

        return User.builder()
                .email(request.getEmail())
                .name(request.getName())
                .picture(request.getPicture())
                .role(request.getRole())
                .joinedAt(LocalDateTime.now())
                .userLevel(lvl)
                .tookLevelTest(false)
                .build();
    }

    public UserResponse toUserResponse(User user){
        return UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .role(user.getRole())
                .joinedAt(user.getJoinedAt())
                .name(user.getName())
                .picture(user.getPicture())
                .watchedVideos(user.getWatchedVideos()
                        .stream()
                        .map(Video::getId)
                        .collect(Collectors.toSet()))
                .studentLevel(user.getUserLevel().getName())
                .tookLevelTest(user.isTookLevelTest())
                .build();
    }
}

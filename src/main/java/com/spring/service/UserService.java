package com.spring.service;

import com.spring.dto.paging.PagingResponse;
import com.spring.dto.paging.UserPagingResponse;
import com.spring.dto.requests.UserRequest;
import com.spring.dto.responses.UserResponse;
import com.spring.dto.responses.VideoResponse;
import com.spring.mapper.UserMapper;
import com.spring.model.User;
import com.spring.model.Video;
import com.spring.repository.LevelRepo;
import com.spring.repository.UserRepo;
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
public class UserService {
    private final UserRepo userRepo;
    private final LevelRepo levelRepo;
    private final UserMapper mapper;

    public UserPagingResponse getAllUsers(int pageNo,int pageSize){
        Pageable pageable = PageRequest.of(pageNo,pageSize,
                Sort.by("joinedAt").descending());

        Page<User> data = userRepo.findAll(pageable);

        List<UserResponse> list = data.getContent()
                .stream()
                .map(mapper::toUserResponse)
                .toList();

        return UserPagingResponse.builder()
                .data(list)
                .totalPages(data.getTotalPages())
                .totalElements(data.getTotalElements())
                .pageNo(data.getNumber())
                .pageSize(data.getSize())
                .isLast(data.isLast())
                .build();
    }

    public void deleteUser(long id){
        User user = userRepo.findById(id)
                .orElseThrow(()->new EntityNotFoundException("User not found"));
        userRepo.delete(user);
    }

    public UserResponse getById(long id){
        return mapper.toUserResponse(userRepo.findById(id)
                .orElseThrow(()->new EntityNotFoundException("User not found")));
    }

    public UserResponse updateUserInfo(long id,UserRequest request){
        var user = userRepo.findById(id)
                .orElseThrow(()->new EntityNotFoundException("User not found"));

        user.setEmail(request.getEmail());
        user.setRole(request.getRole());
        user.setName(request.getName());
        user.setPicture(request.getPicture());

        return mapper.toUserResponse(userRepo.save(user));
    }

    public UserResponse getByEmail(String email){
        var user = userRepo.findByEmail(email);
        if(user == null)
            throw new EntityNotFoundException("User not found with that email");

        return mapper.toUserResponse(user);
    }

}

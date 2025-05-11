package com.spring.service;

import com.spring.dto.requests.UserRequest;
import com.spring.dto.responses.UserResponse;
import com.spring.mapper.UserMapper;
import com.spring.model.User;
import com.spring.repository.UserRepo;
import com.spring.security.JwtService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserAuthenticationService {
    private final UserRepo userRepo;
    private final JwtService jwtService;
    private final UserMapper mapper;

    public UserResponse register(UserRequest request){

        User user = mapper.toUser(request);

        return mapper.toUserResponse(userRepo.save(user));
    }
    public String verifyAndGenerateToken(String email){
        User user = userRepo.findByEmail(email);
        if (user != null){
            return jwtService.GenerateToken(email);
        }
        else
            throw new UsernameNotFoundException("Email not found");
    }

}

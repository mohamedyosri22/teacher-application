package com.spring.controller;

import com.spring.dto.requests.UserRequest;
import com.spring.dto.responses.UserResponse;
import com.spring.service.UserAuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final UserAuthenticationService userService;

    @PostMapping
    public ResponseEntity<UserResponse> registerUser(@RequestBody UserRequest request){
        return ResponseEntity.ok(userService.register(request));
    }

    @GetMapping
    public ResponseEntity<String> generateToken(@RequestParam("email")String email){
        return new ResponseEntity<>(userService.verifyAndGenerateToken(email),
                HttpStatus.OK);
    }
}

package com.spring.controller;


import com.spring.dto.paging.UserPagingResponse;
import com.spring.dto.requests.UserRequest;
import com.spring.dto.responses.UserResponse;
import com.spring.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<UserPagingResponse> getAllUsers(@RequestParam(name = "pageNo", required = false, defaultValue = "0") int pageNo
            , @RequestParam(name = "pageSize", required = false, defaultValue = "20") int pageSize) {
        return ResponseEntity.ok(userService.getAllUsers(pageNo, pageSize));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getById(@PathVariable long id) {
        return ResponseEntity.ok(userService.getById(id));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteById(@RequestParam("id")long id){
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{email}")
    public ResponseEntity<UserResponse> getByEmail(@PathVariable String email){
        return ResponseEntity.ok(userService.getByEmail(email));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUserData(@PathVariable long id,@RequestBody UserRequest request){
        return ResponseEntity.ok(userService.updateUserInfo(id,request));
    }
}

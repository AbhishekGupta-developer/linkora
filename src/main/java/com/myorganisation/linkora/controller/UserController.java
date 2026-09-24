package com.myorganisation.linkora.controller;

import com.myorganisation.linkora.dto.request.UserRequestDto;
import com.myorganisation.linkora.dto.response.UserResponseDto;
import com.myorganisation.linkora.service.UserService;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserResponseDto> registerUser(@RequestBody UserRequestDto userRequestDto) {
        return new ResponseEntity<>(userService.registerUser(userRequestDto), HttpStatusCode.valueOf(201));
    }
}

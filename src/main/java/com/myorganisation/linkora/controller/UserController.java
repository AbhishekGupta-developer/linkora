package com.myorganisation.linkora.controller;

import com.myorganisation.linkora.dto.request.UserRequestDto;
import com.myorganisation.linkora.dto.response.GenericResponseDto;
import com.myorganisation.linkora.dto.response.UserResponseDto;
import com.myorganisation.linkora.enums.Gender;
import com.myorganisation.linkora.service.UserService;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUser(@PathVariable Long id) {
        return new ResponseEntity<>(userService.getUser(id), HttpStatusCode.valueOf(200));
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatusCode.valueOf(200));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable Long id, @RequestBody UserRequestDto userRequestDto) {
        return new ResponseEntity<>(userService.updateUser(id, userRequestDto), HttpStatusCode.valueOf(200));
    }

    @DeleteMapping
    public ResponseEntity<GenericResponseDto> removeUser(@RequestParam Long id) {
        return new ResponseEntity<>(userService.removeUser(id), HttpStatusCode.valueOf(200));
    }

    @GetMapping("/search/email/{email}")
    public ResponseEntity<UserResponseDto> searchUserByEmail(@PathVariable String email) {
        return new ResponseEntity<>(userService.searchUserByEmail(email), HttpStatusCode.valueOf(200));
    }

    @GetMapping("/search/firstName/{firstName}")
    public ResponseEntity<List<UserResponseDto>> searchUsersByFirstName(@PathVariable String firstName) {
        return new ResponseEntity<>(userService.searchUsersByFirstName(firstName),  HttpStatusCode.valueOf(200));
    }

    @GetMapping("/search/filter")
    public ResponseEntity<List<UserResponseDto>> searchUsersWithFilter(
            @RequestParam String name,
            @RequestParam Gender gender
    ) {
        return new ResponseEntity<>(userService.searchUsersWithFilter(name, gender), HttpStatusCode.valueOf(200));
    }
}

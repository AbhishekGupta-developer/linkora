package com.myorganisation.linkora.service;

import com.myorganisation.linkora.dto.request.UserRequestDto;
import com.myorganisation.linkora.dto.response.GenericResponseDto;
import com.myorganisation.linkora.dto.response.UserResponseDto;
import com.myorganisation.linkora.entity.User;
import com.myorganisation.linkora.enums.Gender;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {
    UserResponseDto registerUser(UserRequestDto userRequestDto);
    UserResponseDto getUser(Long id);
    List<UserResponseDto> getAllUsers();
    UserResponseDto updateUser(Long id, UserRequestDto userRequestDto);
    GenericResponseDto removeUser(Long id);

    UserResponseDto searchUserByEmail(String email);
    List<UserResponseDto> searchUsersByFirstName(String firstName);
    List<UserResponseDto> searchUsersWithFilter(String name, Gender gender);

    Page<User> getUserPage(int pageIndex, int pageSize, String sortBy, String sortOrder);
    Page<UserResponseDto> getUserResponsePage(int pageIndex, int pageSize, String sortBy, String sortOrder);
}

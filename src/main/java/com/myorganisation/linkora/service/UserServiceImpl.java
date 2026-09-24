package com.myorganisation.linkora.service;

import com.myorganisation.linkora.dto.request.UserRequestDto;
import com.myorganisation.linkora.dto.response.GenericResponseDto;
import com.myorganisation.linkora.dto.response.UserResponseDto;
import com.myorganisation.linkora.entity.User;
import com.myorganisation.linkora.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    // DEPENDENCY INJECTION USING AUTOWIRED ANNOTATION => NOT RECOMMENDED
//    @Autowired
//    private UserRepository userRepository;


    // DEPENDENCY INJECTION USING CONSTRUCTOR (PARAMETERISED) INJECTION => RECOMMENDED
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserResponseDto registerUser(UserRequestDto userRequestDto) {
        User user = new User();

        user.setFirstName(userRequestDto.getFirstName());
        user.setLastName(userRequestDto.getLastName());
        user.setGender(userRequestDto.getGender());
        user.setEmail(userRequestDto.getEmail());
        user.setPassword(userRequestDto.getPassword());

        userRepository.save(user);

        UserResponseDto userResponseDto = new UserResponseDto();

        userResponseDto.setId(user.getId());
        userResponseDto.setFirstName(user.getFirstName());
        userResponseDto.setLastName(user.getLastName());
        userResponseDto.setGender(user.getGender());
        userResponseDto.setEmail(user.getEmail());

        return userResponseDto;
    }

    @Override
    public UserResponseDto getUser(Long id) {
        return null;
    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        return List.of();
    }

    @Override
    public UserResponseDto updateUser(Long id, UserRequestDto userRequestDto) {
        return null;
    }

    @Override
    public GenericResponseDto removeUser(Long id) {
        return null;
    }
}

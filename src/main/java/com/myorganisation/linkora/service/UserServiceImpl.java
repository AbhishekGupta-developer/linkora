package com.myorganisation.linkora.service;

import com.myorganisation.linkora.dto.request.UserRequestDto;
import com.myorganisation.linkora.dto.response.GenericResponseDto;
import com.myorganisation.linkora.dto.response.UserResponseDto;
import com.myorganisation.linkora.entity.User;
import com.myorganisation.linkora.enums.Gender;
import com.myorganisation.linkora.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        User user = mapUserRequestDtoToUser(userRequestDto, new User());
        userRepository.save(user);
        return mapUserToUserResponseDto(user);
    }

    @Override
    public UserResponseDto getUser(Long id) {
//        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User doesn't exist"));

        User user = userRepository.findById(id).orElse(null);

        return (user != null) ? mapUserToUserResponseDto(user) : null;
    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        List<User> userList = userRepository.findAll();
        List<UserResponseDto> userResponseDtoList = new ArrayList<>();

        for(User user : userList) {
            userResponseDtoList.add(mapUserToUserResponseDto(user));
        }

        return userResponseDtoList;
    }

    @Override
    public UserResponseDto updateUser(Long id, UserRequestDto userRequestDto) {
        User user = userRepository.findById(id).orElse(null);

        if(user == null) {
            return null;
        }

        user = mapUserRequestDtoToUser(userRequestDto, user);

        userRepository.save(user);

        return mapUserToUserResponseDto(user);
    }

    @Override
    public GenericResponseDto removeUser(Long id) {
        User user = userRepository.findById(id).orElse(null);
        GenericResponseDto genericResponseDto = new GenericResponseDto();

        if(user == null) {
            // User doesn't exist in DB
            genericResponseDto.setSuccess(false);
            genericResponseDto.setMessage("User not found");
        } else {
            userRepository.deleteById(id);
            genericResponseDto.setSuccess(true);
            genericResponseDto.setMessage("User name: " + user.getFirstName() + " " + user.getLastName() + " has been removed");
        }

        return genericResponseDto;
    }

    @Override
    public UserResponseDto searchUserByEmail(String email) {
        User user = userRepository.findByEmail(email).orElse(null);
        return (user != null) ? mapUserToUserResponseDto(user) : null;
    }

    @Override
    public List<UserResponseDto> searchUsersByFirstName(String firstName) {
        List<User> userList = userRepository.findByFirstName(firstName);
        List<UserResponseDto> userResponseDtoList = new ArrayList<>();

        for(User user : userList) {
            userResponseDtoList.add(mapUserToUserResponseDto(user));
        }

        return userResponseDtoList;
    }

    @Override
    public List<UserResponseDto> searchUsersWithFilter(String name, Gender gender) {
        List<User> userList = userRepository.findByGenderAndFirstNameContainingOrLastNameContaining(gender, name, name);
        List<UserResponseDto> userResponseDtoList = new ArrayList<>();

        for(User user : userList) {
            userResponseDtoList.add(mapUserToUserResponseDto(user));
        }

        return userResponseDtoList;
    }

    // helper methods

    // map User to UserResponseDto
    private UserResponseDto mapUserToUserResponseDto(User user) {
        UserResponseDto userResponseDto = new UserResponseDto();

        userResponseDto.setId(user.getId());
        userResponseDto.setFirstName(user.getFirstName());
        userResponseDto.setLastName(user.getLastName());
        userResponseDto.setGender(user.getGender());
        userResponseDto.setEmail(user.getEmail());

        return userResponseDto;
    }

    // map UserRequestDto to User
    private User mapUserRequestDtoToUser(UserRequestDto userRequestDto, User user) {
        user.setFirstName(userRequestDto.getFirstName());
        user.setLastName(userRequestDto.getLastName());
        user.setGender(userRequestDto.getGender());
        user.setEmail(userRequestDto.getEmail());
        user.setPassword(userRequestDto.getPassword());

        return user;
    }
}

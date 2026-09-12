package com.myorganisation.linkora.dto.response;

import com.myorganisation.linkora.enums.Gender;
import lombok.Data;

@Data
public class UserResponseDto {
    private Long id;
    private String firstName;
    private String lastName;
    private Gender gender;
    private String email;
}

package com.myorganisation.linkora.dto.request;

import com.myorganisation.linkora.enums.Gender;
import lombok.Data;

@Data
public class UserRequestDto {
    private String firstName;
    private String lastName;
    private Gender gender;
    private String email;
    private String password;
}

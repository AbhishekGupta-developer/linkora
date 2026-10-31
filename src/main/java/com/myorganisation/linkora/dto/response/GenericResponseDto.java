package com.myorganisation.linkora.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GenericResponseDto {
    private String message;
    private Boolean success;
    private Object details;
}

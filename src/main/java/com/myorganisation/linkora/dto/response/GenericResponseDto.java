package com.myorganisation.linkora.dto.response;

import lombok.Data;

@Data
public class GenericResponseDto {
    private String message;
    private Boolean success;
    private Object details;
}

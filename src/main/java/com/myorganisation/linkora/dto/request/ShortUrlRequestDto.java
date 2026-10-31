package com.myorganisation.linkora.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class ShortUrlRequestDto {
    private Long userId;
    private String originalUrl;
    private String shortCode;
    private List<Long> tags;
}

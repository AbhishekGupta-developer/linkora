package com.myorganisation.linkora.service;

import com.myorganisation.linkora.dto.request.ShortUrlRequestDto;
import com.myorganisation.linkora.dto.response.GenericResponseDto;

public interface ShortUrlService {
    GenericResponseDto generateShortUrl(ShortUrlRequestDto shortUrlRequestDto);
    String getOriginalUrl(String shortCode);
}

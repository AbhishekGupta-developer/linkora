package com.myorganisation.linkora.service;

import com.myorganisation.linkora.dto.request.ShortUrlRequestDto;
import com.myorganisation.linkora.dto.response.GenericResponseDto;
import com.myorganisation.linkora.entity.ShortUrl;
import com.myorganisation.linkora.entity.Tag;
import com.myorganisation.linkora.entity.User;
import com.myorganisation.linkora.repository.ShortUrlRepository;
import com.myorganisation.linkora.repository.TagRepository;
import com.myorganisation.linkora.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ShortUrlServiceImpl implements ShortUrlService {

    @Autowired
    private ShortUrlRepository shortUrlRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TagRepository tagRepository;

    @Override
    public GenericResponseDto generateShortUrl(ShortUrlRequestDto shortUrlRequestDto) {
        User user = userRepository.findById(shortUrlRequestDto.getUserId()).orElse(null);
        if(user == null) {
            throw new RuntimeException("User doesn't exist");
        }

        ShortUrl shortUrl = new ShortUrl();
        shortUrl.setOriginalUrl(shortUrlRequestDto.getOriginalUrl());
        shortUrl.setShortCode(shortUrlRequestDto.getShortCode());
        shortUrl.setUser(user);

        List<Long> tags = shortUrlRequestDto.getTags();

        for(Long tagId : tags) {
            Tag tag = tagRepository.findById(tagId).orElse(null);
            if(tag != null) {
            shortUrl.getTags().add(tag);
            }
        }

        shortUrlRepository.save(shortUrl);

        return new GenericResponseDto("Short url created!", true, Map.of("shortUrl", "http://localhost/" + shortUrlRequestDto.getShortCode()));
    }

    @Override
    public String getOriginalUrl(String shortCode) {
        ShortUrl shortUrl = shortUrlRepository.findByShortCode(shortCode).orElse(null);
        if(shortUrl == null) {
            throw new RuntimeException("Short url doesn't exist");
        }

        return shortUrl.getOriginalUrl();
    }
}

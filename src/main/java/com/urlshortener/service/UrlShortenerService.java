package com.urlshortener.service;

import com.urlshortener.dto.UrlRequestDto;
import com.urlshortener.dto.AllUrlDto;
import com.urlshortener.entity.UrlEntity;
import com.urlshortener.mapper.UrlMapper;
import com.urlshortener.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UrlShortenerService {

    private final UrlMapper urlMapper;
    private final UrlRepository urlRepository;

    @Autowired
    public UrlShortenerService(UrlMapper urlMapper, UrlRepository urlRepository) {
        this.urlMapper = urlMapper;
        this.urlRepository = urlRepository;
    }

    public AllUrlDto createShortUrl(UrlRequestDto requestDto) {
        UrlEntity urlEntity = urlMapper.toEntity(requestDto);

        if (urlEntity.getShortUrl() == null || urlEntity.getShortUrl().isEmpty()) {
            urlEntity.setShortUrl(generateShortUrl());
        }

        urlEntity = urlRepository.save(urlEntity);
        return urlMapper.toResponseDto(urlEntity);
    }

    private String generateShortUrl() {
        return UUID.randomUUID().toString().substring(0, 8);
    }

    public Optional<String> getOriginalUrl(String shortUrl) {
        Optional<UrlEntity> urlEntityOptional = urlRepository.findByShortUrl(shortUrl);
        return urlEntityOptional.map(UrlEntity::getOriginalUrl);
    }

    public List<AllUrlDto> getAllUrls() {
        List<UrlEntity> urlEntities = urlRepository.findAll();
        return urlEntities.stream()
                .map(urlMapper::toResponseDto)
                .toList();
    }

    public void deleteShortUrl(String id) {
        urlRepository.deleteById(id);
    }

}

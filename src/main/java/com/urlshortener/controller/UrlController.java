package com.urlshortener.controller;

import com.urlshortener.dto.OriginalUrlDto;
import com.urlshortener.dto.UrlRequestDto;
import com.urlshortener.dto.AllUrlDto;
import com.urlshortener.service.UrlShortenerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/urls")
public class UrlController {

    private final UrlShortenerService urlShortenerService;

    @Autowired
    public UrlController(UrlShortenerService urlShortenerService) {
        this.urlShortenerService = urlShortenerService;
    }

    @PostMapping
    public AllUrlDto createShortUrl(@RequestBody UrlRequestDto requestDto) {
        return urlShortenerService.createShortUrl(requestDto);
    }

    @GetMapping("/{shortUrl}")
    public ResponseEntity<OriginalUrlDto> getOriginalUrl(@PathVariable String shortUrl) {
        Optional<String> originalUrlOptional = urlShortenerService.getOriginalUrl(shortUrl);
        if (originalUrlOptional.isPresent()) {
            OriginalUrlDto clickableUrlDto = new OriginalUrlDto(originalUrlOptional.get());
            return ResponseEntity.ok(clickableUrlDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public List<AllUrlDto> getAllUrls() {
        return urlShortenerService.getAllUrls();
    }

    @DeleteMapping("/{id}")
    public void deleteShortUrl(@PathVariable String id) {
        urlShortenerService.deleteShortUrl(id);
    }

    @GetMapping("/goto/{shortUrl}")
    public ResponseEntity<Void> redirectToOriginalUrl(@PathVariable String shortUrl) {
        Optional<String> originalUrlOptional = urlShortenerService.getOriginalUrl(shortUrl);
        if (originalUrlOptional.isPresent()) {
            String originalUrl = originalUrlOptional.get();
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(URI.create(originalUrl));
            return new ResponseEntity<>(headers, HttpStatus.FOUND); // 302 Found status code
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}

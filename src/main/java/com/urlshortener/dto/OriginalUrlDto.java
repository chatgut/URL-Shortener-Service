package com.urlshortener.dto;

public class OriginalUrlDto {

    private String originalUrl;

    public OriginalUrlDto(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

}

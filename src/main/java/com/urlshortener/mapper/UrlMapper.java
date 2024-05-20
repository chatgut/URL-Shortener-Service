package com.urlshortener.mapper;

import com.urlshortener.dto.UrlRequestDto;
import com.urlshortener.dto.AllUrlDto;
import com.urlshortener.entity.UrlEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UrlMapper {

    UrlEntity toEntity(UrlRequestDto dto);

    AllUrlDto toResponseDto(UrlEntity entity);

}

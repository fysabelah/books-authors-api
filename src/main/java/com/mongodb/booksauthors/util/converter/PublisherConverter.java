package com.mongodb.booksauthors.util.converter;

import com.mongodb.booksauthors.business.documents.Publisher;
import com.mongodb.booksauthors.util.dto.PublisherDto;
import org.springframework.stereotype.Component;

@Component
public class PublisherConverter implements Converter<Publisher, PublisherDto> {

    @Override
    public PublisherDto convert(Publisher document) {
        return null;
    }

    @Override
    public Publisher convert(PublisherDto dto) {
        return null;
    }
}

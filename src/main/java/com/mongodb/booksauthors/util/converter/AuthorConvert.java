package com.mongodb.booksauthors.util.converter;

import com.mongodb.booksauthors.business.documents.Author;
import com.mongodb.booksauthors.util.dto.AuthorDto;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class AuthorConvert implements Converter<Author, AuthorDto> {

    @Override
    public AuthorDto convert(Author document) {
        return new AuthorDto(document.getId(),
                document.getName(),
                document.getBirthdate());
    }

    @Override
    public Author convert(AuthorDto dto) {
        LocalDate date = LocalDate.parse(dto.getBirthdate(), DateTimeFormatter.ISO_LOCAL_DATE);

        return new Author(dto.getId(),
                dto.getName(),
                date);
    }
}

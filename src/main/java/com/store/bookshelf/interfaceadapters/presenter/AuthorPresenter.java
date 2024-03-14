package com.store.bookshelf.interfaceadapters.presenter;

import com.store.bookshelf.interfaceadapters.presenter.dto.AuthorDto;
import com.store.bookshelf.entities.Author;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class AuthorPresenter implements Presenter<Author, AuthorDto> {

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

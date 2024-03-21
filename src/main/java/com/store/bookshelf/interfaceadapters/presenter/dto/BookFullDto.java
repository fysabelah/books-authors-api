package com.store.bookshelf.interfaceadapters.presenter.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
public class BookFullDto extends BookDto {

    private List<AuthorDto> authors;

    private List<PublisherDto> publishers;

    public BookFullDto(BookDto bookDto, List<AuthorDto> authors, List<PublisherDto> publishers) {
        super(bookDto.getId(), bookDto.getTitle(), bookDto.getGenre(), bookDto.getPages());
        this.authors = authors;
        this.publishers = publishers;
    }
}

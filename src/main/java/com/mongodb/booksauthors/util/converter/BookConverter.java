package com.mongodb.booksauthors.util.converter;

import com.mongodb.booksauthors.business.documents.Book;
import com.mongodb.booksauthors.util.dto.BookDto;
import org.springframework.stereotype.Component;

@Component
public class BookConverter implements Converter<Book, BookDto> {

    @Override
    public BookDto convert(Book document) {
        return new BookDto(document.getId(),
                document.getTitle(),
                document.getGenre(),
                document.getPages());
    }

    @Override
    public Book convert(BookDto dto) {
        return new Book(dto.getId(),
                dto.getTitle(),
                dto.getGenre(),
                dto.getPages());
    }
}

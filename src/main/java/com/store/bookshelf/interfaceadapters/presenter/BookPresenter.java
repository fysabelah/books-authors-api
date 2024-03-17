package com.store.bookshelf.interfaceadapters.presenter;

import com.store.bookshelf.entities.Book;
import com.store.bookshelf.interfaceadapters.presenter.dto.BookDto;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

@Component
public class BookPresenter implements Presenter<Book, BookDto> {

    @Resource
    private AuthorPresenter authorPresenter;

    @Override
    public BookDto convert(Book document) {
        return new BookDto(document.getId(),
                document.getTitle(),
                document.getGenre(),
                document.getPages(),
                authorPresenter.convertEntities(document.getAuthors()));
    }

    @Override
    public Book convert(BookDto dto) {
        return new Book(dto.getId(),
                dto.getTitle(),
                dto.getGenre(),
                dto.getPages(),
                authorPresenter.convert(dto.getAuthors()),
                null);
    }
}

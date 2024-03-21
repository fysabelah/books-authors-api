package com.store.bookshelf.interfaceadapters.presenter;

import com.store.bookshelf.entities.Author;
import com.store.bookshelf.entities.Book;
import com.store.bookshelf.entities.Publisher;
import com.store.bookshelf.interfaceadapters.presenter.dto.AuthorDto;
import com.store.bookshelf.interfaceadapters.presenter.dto.BookDto;
import com.store.bookshelf.interfaceadapters.presenter.dto.BookFullDto;
import com.store.bookshelf.interfaceadapters.presenter.dto.PublisherDto;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookPresenter implements Presenter<Book, BookDto> {

    @Resource
    private AuthorPresenter authorPresenter;

    @Resource
    private PublisherPresenter publisherPresenter;

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

    public BookFullDto convert(Book book, List<Author> authors, List<Publisher> publishers) {
        BookDto bookDto = convert(book);
        List<AuthorDto> authorDtos = authorPresenter.convertEntities(authors);
        List<PublisherDto> publisherDtos = publisherPresenter.convertEntities(publishers);

        return new BookFullDto(bookDto, authorDtos, publisherDtos);
    }
}

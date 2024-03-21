package com.store.bookshelf.interfaceadapters.controllers;

import com.store.bookshelf.entities.Author;
import com.store.bookshelf.entities.Book;
import com.store.bookshelf.entities.Publisher;
import com.store.bookshelf.interfaceadapters.gateway.AuthorGateway;
import com.store.bookshelf.interfaceadapters.gateway.BookGateway;
import com.store.bookshelf.interfaceadapters.gateway.PublisherGateway;
import com.store.bookshelf.interfaceadapters.presenter.BookPresenter;
import com.store.bookshelf.interfaceadapters.presenter.dto.BookCreateDto;
import com.store.bookshelf.interfaceadapters.presenter.dto.BookDto;
import com.store.bookshelf.interfaceadapters.presenter.dto.BookFullDto;
import com.store.bookshelf.usercase.BookBusiness;
import com.store.bookshelf.util.MessageUtil;
import com.store.bookshelf.util.enums.Genre;
import com.store.bookshelf.util.exceptions.ValidationsException;
import com.store.bookshelf.util.pagination.PagedResponse;
import com.store.bookshelf.util.pagination.Pagination;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class BookController {

    @Resource
    private BookGateway gateway;

    @Resource
    private AuthorGateway authorGateway;

    @Resource
    private PublisherGateway publisherGateway;

    @Resource
    private BookBusiness business;

    @Resource
    private BookPresenter presenter;

    @Transactional
    public BookFullDto insert(BookCreateDto dto) throws ValidationsException {
        List<Author> authors = authorGateway.findAllById(dto.getAuthors());
        List<Publisher> publishers = publisherGateway.findAllById(dto.getPublishers());

        Book book = presenter.convert(dto);

        business.create(authors, publishers);

        book = gateway.insert(book);

        authors = authorGateway.addBook(authors, book);
        publishers = publisherGateway.addBook(publishers, book);

        return presenter.convert(book, authors, publishers);
    }

    public BookDto update(Integer id, String title, Genre genre, Integer pages) throws ValidationsException {
        if (title == null && genre == null && pages == null) {
            throw new IllegalArgumentException(MessageUtil.getMessage("0006"));
        }

        Book book = gateway.findById(id);

        business.update(book, title, genre, pages);

        book = gateway.update(book);

        return presenter.convert(book);
    }

    public PagedResponse<BookDto> findAll(Pagination pagination, String title) {
        Pageable pageable = PageRequest.of(pagination.getPage(),
                pagination.getPageSize(),
                Sort.by(Sort.Direction.ASC, "title"));

        Page<Book> page;

        if (title == null || title.trim().isEmpty()) {
            page = gateway.findAll(pageable);
        } else {
            page = gateway.findByTitleIgnoreCase(title, pageable);
        }

        return presenter.convertEntities(page);
    }

    public void delete(Integer id) throws ValidationsException {
        Book book = gateway.findById(id);

        gateway.delete(book);
    }

    public BookDto findById(Integer id, boolean isFullBook) throws ValidationsException {
        Book book = gateway.findById(id);

        if (!isFullBook) {
            return presenter.convert(book);
        }

        List<Author> authors = authorGateway.findByBookId(id);
        List<Publisher> publisher = publisherGateway.findByBookId(id);

        return presenter.convert(book, authors, publisher);
    }

    public void removeAuthor(Integer bookId, Integer authorId) throws ValidationsException {
        Book book = gateway.findByBookIdAndAuthorId(bookId, authorId);

        List<Author> authors = authorGateway.findAllAuthorsOfBook(bookId);

        business.removeAuthor(authors, book.getId());

        authorGateway.removeBookFromAuthor(authorId, book);
    }

    public void removePublisher(Integer bookId, Integer publisherId) throws ValidationsException {
        Book book = gateway.findByBookIdAndPublisherId(bookId, publisherId);

        List<Publisher> publishers = publisherGateway.findAllPublishersOfBook(bookId);

        business.removePublisher(publishers, bookId);

        publisherGateway.removeBookFromPublisher(publisherId, book);
    }
}

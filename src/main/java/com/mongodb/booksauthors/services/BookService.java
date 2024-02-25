package com.mongodb.booksauthors.services;

import com.mongodb.booksauthors.business.documents.Author;
import com.mongodb.booksauthors.business.documents.Book;
import com.mongodb.booksauthors.business.repository.BookRepository;
import com.mongodb.booksauthors.util.converter.BookConverter;
import com.mongodb.booksauthors.util.dto.AuthorDto;
import com.mongodb.booksauthors.util.dto.BookDto;
import com.mongodb.booksauthors.util.enums.Genre;
import com.mongodb.booksauthors.util.exceptions.ValidationsException;
import com.mongodb.booksauthors.util.pagination.PagedResponse;
import com.mongodb.booksauthors.util.pagination.Pagination;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    @Resource
    private BookRepository repository;

    @Resource
    private BookConverter converter;

    @Resource
    private AuthorService authorService;

    public BookDto insert(BookDto dto) throws ValidationsException {
        List<AuthorDto> authors = new ArrayList<>();

        for (AuthorDto authorDto : dto.getAuthors()) {
            if (authorDto.getId() == null) {
                authors.add(authorService.insert(authorDto));
            } else {
                authors.add(authorService.findById(authorDto.getId()));
            }
        }

        dto.setAuthors(authors);

        Book book = converter.convert(dto);
        book = repository.insert(book);

        authorService.addBookOnAuthors(book.getAuthors(), book);

        return converter.convert(book);
    }

    public BookDto findById(String id) throws ValidationsException {
        Book book = getBook(id);

        return converter.convert(book);
    }

    private Book getBook(String id) throws ValidationsException {
        return repository.findById(id)
                .orElseThrow(() -> new ValidationsException("0100"));
    }

    public PagedResponse<BookDto> findAll(Pagination pagination, String title) {
        Pageable page = PageRequest.of(pagination.getPage(),
                pagination.getPageSize(),
                Sort.by(Sort.Direction.ASC, "title"));

        Page<Book> result;

        if (title == null || title.trim().isEmpty()) {
            result = repository.findAll(page);
        } else {
            result = repository.findByTitleLikeIgnoresCase(title, page);
        }

        return converter.convertDocuments(result);
    }

    public void delete(String id) throws ValidationsException {
        Book book = getBook(id);

        authorService.removeBook(id);

        repository.delete(book);
    }

    public BookDto update(String id, String title, Genre genre, Integer pages) throws ValidationsException {
        Book book = getBook(id);

        if (title != null && !title.trim().isEmpty()) {
            book.setTitle(title);
        }

        if (genre != null) {
            book.setGenre(genre);
        }

        if (pages != null && pages > 0) {
            book.setPages(pages);
        }

        book = repository.save(book);

        return converter.convert(book);
    }

    public BookDto removeAuthor(String id, String authorId) throws ValidationsException {
        Book book = repository.findBookByIdAndAuthorsId(id, authorId)
                .orElseThrow(() -> new ValidationsException("0100"));

        List<Author> authors = book.getAuthors().stream()
                .filter(author -> !author.getId().equals(authorId))
                .toList();

        if (authors.isEmpty()) {
            throw new ValidationsException("0101");
        }

        book.setAuthors(authors);

        book = repository.save(book);

        authorService.removeBook(authorId, id);

        return converter.convert(book);
    }
}

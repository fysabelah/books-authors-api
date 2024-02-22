package com.mongodb.booksauthors.services;

import com.mongodb.booksauthors.business.documents.Book;
import com.mongodb.booksauthors.business.repository.BookRepository;
import com.mongodb.booksauthors.util.converter.BookConverter;
import com.mongodb.booksauthors.util.dto.BookDto;
import com.mongodb.booksauthors.util.exceptions.ValidationsException;
import com.mongodb.booksauthors.util.pagination.PagedResponse;
import com.mongodb.booksauthors.util.pagination.Pagination;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    @Resource
    private BookRepository repository;

    @Resource
    private BookConverter converter;

    public BookDto insert(BookDto dto) {
        Book book = converter.convert(dto);

        return converter.convert(repository.insert(book));
    }

    public BookDto update(BookDto book) throws ValidationsException {
        Book saved = getBook(book.getId());

        saved.setGenre(book.getGenre());
        saved.setTitle(book.getTitle());
        saved.setPages(book.getPages());

        return converter.convert(repository.save(saved));
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

        repository.delete(book);
    }
}

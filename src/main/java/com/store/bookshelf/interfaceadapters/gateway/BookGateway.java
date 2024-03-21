package com.store.bookshelf.interfaceadapters.gateway;

import com.store.bookshelf.entities.Book;
import com.store.bookshelf.frameworks.db.books.BookRepository;
import com.store.bookshelf.util.MessageUtil;
import com.store.bookshelf.util.exceptions.ValidationsException;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BookGateway extends GenericGateway<Book> {

    @Resource
    private BookRepository repository;

    public Page<Book> findByTitleIgnoreCase(String title, Pageable page) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException(MessageUtil.getMessage("0001", "T\u00EDtulo"));
        }

        return repository.findByTitleIgnoreCaseLike(title, page);
    }

    public Book insert(Book book) {
        return super.insert(book, repository);
    }

    public Book findById(Integer id) throws ValidationsException {
        return super.findById(id, repository);
    }

    public Book update(Book entity) {
        return super.update(entity, repository);
    }

    public void delete(Book entity) {
        super.delete(entity, repository);
    }

    public Page<Book> findAll(Pageable page) {
        return super.findAll(page, repository);
    }

    public Book findByBookIdAndAuthorId(Integer bookId, Integer authorId) throws ValidationsException {
        return repository.findByBookIdAndAuthorId(bookId, authorId)
                .orElseThrow(() -> new ValidationsException("0101"));
    }

    public Book findByBookIdAndPublisherId(Integer bookId, Integer publisherId) throws ValidationsException {
        return repository.findByBookIdAndPublisherId(bookId, publisherId)
                .orElseThrow(() -> new ValidationsException("0101"));
    }
}

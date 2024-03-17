package com.store.bookshelf.interfaceadapters.gateway;

import com.store.bookshelf.entities.Book;
import com.store.bookshelf.frameworks.db.BookRepository;
import com.store.bookshelf.util.MessageUtil;
import com.store.bookshelf.util.exceptions.ValidationsException;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookGateway {

    @Resource
    private BookRepository repository;

    public Book findByIdAndAuthor(Integer bookId, Integer authorId) throws ValidationsException {
        if (bookId == null || authorId == null) {
            throw new IllegalArgumentException(MessageUtil.getMessage("0001", "Id"));
        }

        return repository.findBookByIdAndAuthorsId(bookId, authorId)
                .orElseThrow(() -> new ValidationsException("0100"));
    }

    public Page<Book> findByTitleIgnoreCase(String title, Pageable page) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException(MessageUtil.getMessage("0001", "T\u00EDtulo"));
        }

        return repository.findByTitleIgnoreCaseLike(title, page);
    }

    public Book insert(Book book) {
        if (book == null) {
            throw new IllegalArgumentException(MessageUtil.getMessage("0002", "Livro"));
        }

        return repository.save(book);
    }

    public List<Book> findAllByAuthorsId(Integer authorId) {
        if (authorId == null) {
            throw new IllegalArgumentException(MessageUtil.getMessage("0001", "Id"));
        }

        return repository.findAllByAuthorsId(authorId)
                .orElse(new ArrayList<>());
    }

    public List<Book> findByPublishersId(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException(MessageUtil.getMessage("0001", "Id"));
        }

        return repository.findAllByPublishersId(id)
                .orElse(new ArrayList<>());
    }
}

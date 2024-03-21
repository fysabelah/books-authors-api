package com.store.bookshelf.interfaceadapters.gateway;

import com.store.bookshelf.entities.Author;
import com.store.bookshelf.entities.Book;
import com.store.bookshelf.frameworks.db.author.AuthorRepository;
import com.store.bookshelf.util.MessageUtil;
import com.store.bookshelf.util.exceptions.ValidationsException;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorGateway extends GenericGateway<Author> {

    @Resource
    private AuthorRepository repository;

    public Author insert(Author entity) {
        return super.insert(entity, repository);
    }

    public Author findById(Integer id) throws ValidationsException {
        return super.findById(id, repository);
    }

    public Author update(Author entity) {
        return super.update(entity, repository);
    }

    public void delete(Author entity) {
        super.delete(entity, repository);
    }

    public Page<Author> findAll(Pageable page) {
        return super.findAll(page, repository);
    }

    public List<Author> findAllById(List<Integer> authors) {
        if (authors == null || authors.isEmpty()) {
            throw new IllegalArgumentException(MessageUtil.getMessage("0001", "Autor"));
        }

        return repository.findAllById(authors);
    }

    public List<Author> addBook(List<Author> authors, Book book) {
        authors.forEach(author -> author.addBook(book));

        return repository.saveAll(authors);
    }

    public List<Author> findByBookId(Integer bookId) throws ValidationsException {
        return repository.findByBooksId(bookId)
                .orElseThrow(() -> new ValidationsException("0007", "autores", bookId.toString()));
    }

    public List<Author> findAllAuthorsOfBook(Integer bookId) {
        if (bookId == null) {
            throw new IllegalArgumentException(MessageUtil.getMessage("0003"));
        }

        return repository.findAllAuthorsOfBook(bookId)
                .orElse(new ArrayList<>());
    }

    public void removeBookFromAuthor(Integer authorId, Book book) throws ValidationsException {
        if (authorId == null || book == null) {
            throw new IllegalArgumentException(MessageUtil.getMessage("0003"));
        }

        Author author = findById(authorId);

        author.getBooks().remove(book);

        repository.save(author);
    }
}

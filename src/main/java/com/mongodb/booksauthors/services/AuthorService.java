package com.mongodb.booksauthors.services;

import com.mongodb.booksauthors.business.documents.Author;
import com.mongodb.booksauthors.business.documents.Book;
import com.mongodb.booksauthors.business.repository.AuthorRepository;
import com.mongodb.booksauthors.util.converter.AuthorConvert;
import com.mongodb.booksauthors.util.dto.AuthorDto;
import com.mongodb.booksauthors.util.exceptions.ValidationsException;
import com.mongodb.booksauthors.util.pagination.PagedResponse;
import com.mongodb.booksauthors.util.pagination.Pagination;
import jakarta.annotation.Resource;
import jakarta.validation.ValidationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorService {

    @Resource
    private AuthorConvert convert;

    @Resource
    private AuthorRepository repository;

    public AuthorDto insert(AuthorDto authorDto) {
        Author author = convert.convert(authorDto);

        author.setId(null);
        author = repository.insert(author);

        return convert.convert(author);
    }

    public AuthorDto findById(String id) throws ValidationsException {
        Author author = getAuthor(id);

        return convert.convert(author);
    }

    private Author getAuthor(String id) throws ValidationsException {
        return repository.findById(id)
                .orElseThrow(() -> new ValidationsException("0200"));
    }

    public void delete(String id) throws ValidationsException {
        Author author = getAuthor(id);

        if (author.getBooks() != null && !author.getBooks().isEmpty()) {
            throw new ValidationsException("0201");
        }

        repository.delete(author);
    }

    public AuthorDto update(AuthorDto dto) throws ValidationsException {
        Author newAuthorData = convert.convert(dto);

        Author author = getAuthor(dto.getId());

        author.setName(newAuthorData.getName());
        author.setBirthdate(newAuthorData.getBirthdate());

        author = repository.save(author);

        return convert.convert(author);
    }

    public PagedResponse<AuthorDto> findAll(Pagination pagination) {
        Pageable page = PageRequest.of(pagination.getPage(),
                pagination.getPageSize(),
                Sort.by(Sort.Direction.ASC, "name"));

        Page<Author> result = repository.findAll(page);

        return convert.convertDocuments(result);
    }

    public void addBookOnAuthors(List<Author> authors, Book book) {
        authors.forEach(author -> {
            if (author.getBooks() == null) {
                author.setBooks(new ArrayList<>());
            }

            author.getBooks().add(book);
        });

        repository.saveAll(authors);
    }

    /**
     Removes the author of a specific book
     - Update of authors of a book
    */
    public void removeBook(String authorId, String bookId) {
        Author author = repository.findAuthorByIdAndBooksId(authorId, bookId)
                .orElseThrow(() -> new ValidationException("0200"));

        List<Book> books = author.getBooks().stream()
                .filter(book -> !book.getId().equals(bookId))
                .toList();

        author.setBooks(books);

        repository.save(author);
    }

    /**
     Remove book from authors
     - Deleting a book
     */
    public void removeBook(String bookId) {
        List<Author> authors = repository.findByBooksId(bookId);
        
        for (Author author : authors) {
            List<Book> books = author.getBooks().stream()
                    .filter(book -> !book.getId().equals(bookId))
                    .toList();

            author.setBooks(books);
        }

        repository.saveAll(authors);
    }
}

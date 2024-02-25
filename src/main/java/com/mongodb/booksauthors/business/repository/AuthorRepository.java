package com.mongodb.booksauthors.business.repository;

import com.mongodb.booksauthors.business.documents.Author;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends MongoRepository<Author, String> {

    Optional<Author> findAuthorByIdAndBooksId(String authorId, String bookId);

    List<Author> findByBooksId(String bookId);
}

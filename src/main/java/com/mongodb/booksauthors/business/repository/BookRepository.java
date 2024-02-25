package com.mongodb.booksauthors.business.repository;

import com.mongodb.booksauthors.business.documents.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends MongoRepository<Book, String> {

    @Query("{'title': {$regex: '(?i)?0'}}")
    Page<Book> findByTitleLikeIgnoresCase(String title, Pageable page);

    Optional<Book> findBookByIdAndAuthorsId(String bookId, String authorId);
}

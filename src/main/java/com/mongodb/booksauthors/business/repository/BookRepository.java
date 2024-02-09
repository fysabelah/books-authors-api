package com.mongodb.booksauthors.business.repository;

import com.mongodb.booksauthors.business.documents.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookRepository extends MongoRepository<Book, String> {

}

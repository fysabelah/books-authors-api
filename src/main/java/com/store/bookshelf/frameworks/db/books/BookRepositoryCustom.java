package com.store.bookshelf.frameworks.db.books;

import com.store.bookshelf.entities.Book;

import java.util.Optional;

interface BookRepositoryCustom {

    Optional<Book> findByBookIdAndAuthorId(Integer bookId, Integer authorId);

    Optional<Book> findByBookIdAndPublisherId(Integer bookId, Integer publisherId);
}

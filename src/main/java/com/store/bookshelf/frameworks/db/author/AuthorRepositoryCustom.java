package com.store.bookshelf.frameworks.db.author;

import com.store.bookshelf.entities.Author;

import java.util.List;
import java.util.Optional;

interface AuthorRepositoryCustom {

    Optional<List<Author>> findAllAuthorsOfBook(Integer bookId);
}

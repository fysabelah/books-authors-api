package com.store.bookshelf.frameworks.db.author;

import com.store.bookshelf.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer>, AuthorRepositoryCustom {

    Optional<List<Author>> findByBooksId(Integer bookId);
}

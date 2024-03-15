package com.store.bookshelf.frameworks.db;

import com.store.bookshelf.entities.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    Page<Book> findByTitleIgnoreCaseLike(String title, Pageable page);

    Optional<Book> findBookByIdAndAuthorsId(Integer bookId, Integer authorId);

    Optional<List<Book>> findAllByAuthorsId(Integer authorId);
}

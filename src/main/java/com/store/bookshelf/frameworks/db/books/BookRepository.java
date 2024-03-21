package com.store.bookshelf.frameworks.db.books;

import com.store.bookshelf.entities.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer>, BookRepositoryCustom {

    Page<Book> findByTitleIgnoreCaseLike(String title, Pageable page);
}

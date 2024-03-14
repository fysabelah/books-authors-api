package com.store.bookshelf.frameworks.db;

import com.store.bookshelf.entities.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {

    Page<Author> findByBirthdate(LocalDate birthdate, Pageable page);

    Optional<Author> findByNameLike(String name);
}

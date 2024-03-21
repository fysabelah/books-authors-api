package com.store.bookshelf.frameworks.db.publisher;

import com.store.bookshelf.entities.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Integer>, PublisherRepositoryCustom {

    Optional<List<Publisher>> findAllByBooksId(Integer bookId);
}

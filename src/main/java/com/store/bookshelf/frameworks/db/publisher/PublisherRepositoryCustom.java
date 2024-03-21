package com.store.bookshelf.frameworks.db.publisher;

import com.store.bookshelf.entities.Publisher;

import java.util.List;
import java.util.Optional;

interface PublisherRepositoryCustom {

    Optional<List<Publisher>> findAllPublishersOfBook(Integer bookId);
}

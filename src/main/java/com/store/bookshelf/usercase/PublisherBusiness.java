package com.store.bookshelf.usercase;

import com.store.bookshelf.entities.Publisher;
import com.store.bookshelf.util.MessageUtil;

public class PublisherBusiness {

    public Publisher update(String name, Publisher publisher) {
        validatePublisher(publisher);

        publisher.setName(name);

        return publisher;
    }

    public Publisher delete(Publisher publisher) {
        validatePublisher(publisher);

        if (publisher.getBooks() != null || !publisher.getBooks().isEmpty()) {
            throw new IllegalArgumentException(MessageUtil.getMessage("0300"));
        }

        publisher.setBooks(null);

        return publisher;
    }

    private static void validatePublisher(Publisher publisher) {
        if (publisher == null) {
            throw new IllegalArgumentException(MessageUtil.getMessage("0003"));
        }
    }
}

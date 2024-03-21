package com.store.bookshelf.usercase;

import com.store.bookshelf.entities.Publisher;
import com.store.bookshelf.util.MessageUtil;
import com.store.bookshelf.util.exceptions.ValidationsException;
import org.springframework.stereotype.Component;

@Component
public class PublisherBusiness {

    public Publisher update(String name, Publisher publisher) {
        validatePublisher(publisher);

        publisher.setName(name);

        return publisher;
    }

    public void delete(Publisher publisher) throws ValidationsException {
        validatePublisher(publisher);

        if (publisher.getBooks() != null && !publisher.getBooks().isEmpty()) {
            throw new ValidationsException("0300");
        }
    }

    private static void validatePublisher(Publisher publisher) {
        if (publisher == null) {
            throw new IllegalArgumentException(MessageUtil.getMessage("0003"));
        }
    }
}

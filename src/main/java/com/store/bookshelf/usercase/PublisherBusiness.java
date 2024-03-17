package com.store.bookshelf.usercase;

import com.store.bookshelf.entities.Book;
import com.store.bookshelf.entities.Publisher;
import com.store.bookshelf.util.MessageUtil;
import com.store.bookshelf.util.exceptions.ValidationsException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PublisherBusiness {

    public Publisher update(String name, Publisher publisher) {
        validatePublisher(publisher);

        publisher.setName(name);

        return publisher;
    }

    public void delete(Publisher publisher, List<Book> books) throws ValidationsException {
        validatePublisher(publisher);

        if (books != null && !books.isEmpty()) {
            throw new ValidationsException("0300");
        }
    }

    private static void validatePublisher(Publisher publisher) {
        if (publisher == null) {
            throw new IllegalArgumentException(MessageUtil.getMessage("0003"));
        }
    }
}

package com.store.bookshelf.usercase;

import com.store.bookshelf.entities.Book;
import com.store.bookshelf.entities.Publisher;
import com.store.bookshelf.util.MessageUtil;
import com.store.bookshelf.util.exceptions.ValidationsException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PublisherBusinessTest {

    private static PublisherBusiness business;

    @BeforeAll
    static void beforeAll() {
        business = new PublisherBusiness();
    }

    @Test
    void testUpdatePublisherName() {
        Publisher savedPublisher = new Publisher();
        savedPublisher.setName("Old Publisher");

        String newName = "New Publisher";

        Publisher updatedPublisher = business.update(newName, savedPublisher);

        assertEquals(newName, updatedPublisher.getName());
    }

    @Test
    void testUpdatePublisherWithNullPublisher() {
        try {
            business.update("New Publisher", null);
        } catch (IllegalArgumentException exception) {
            assertEquals(exception.getMessage(), MessageUtil.getMessage("0003"));
        }
    }

    @Test
    void testDeletePublisher() {
        Publisher publisher = new Publisher();
        publisher.setName("Publisher");

        List<Book> books = new ArrayList<>();

        publisher.setBooks(books);

        assertDoesNotThrow(() -> business.delete(publisher));
    }

    @Test
    void testDeletePublisherWithBooks() {
        Publisher publisher = new Publisher();
        publisher.setName("Publisher");

        List<Book> books = new ArrayList<>();
        books.add(new Book());

        publisher.setBooks(books);

        try {
            business.delete(publisher);
        } catch (ValidationsException exception) {
            assertEquals(MessageUtil.getMessage("0300"), exception.getMessage());
        }
    }

    @Test
    void testDeleteNullPublisher() throws ValidationsException {
        try {
            business.delete(null);
        } catch (IllegalArgumentException exception) {
            assertEquals(exception.getMessage(), MessageUtil.getMessage("0003"));
        }
    }
}
package com.store.bookshelf.usercase;

import com.store.bookshelf.entities.Author;
import com.store.bookshelf.entities.Book;
import com.store.bookshelf.entities.Publisher;
import com.store.bookshelf.util.MessageUtil;
import com.store.bookshelf.util.enums.Genre;
import com.store.bookshelf.util.exceptions.ValidationsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BookBusinessTest {

    private BookBusiness bookBusiness;

    @BeforeEach
    void setUp() {
        bookBusiness = new BookBusiness();
    }

    @Test
    void testUpdateWithNullValues() {
        Book savedBook = new Book(1, "Initial Title", Genre.BIOGRAPHY, 200);

        bookBusiness.update(savedBook, null, null, null);

        assertEquals("Initial Title", savedBook.getTitle());
        assertEquals(Genre.BIOGRAPHY, savedBook.getGenre());
        assertEquals(200, savedBook.getPages());
    }

    @Test
    void testUpdateWithGenre() {
        Book savedBook = new Book(1, "Initial Title", Genre.FANTASY, 200);

        bookBusiness.update(savedBook, null, Genre.HORROR, null);

        assertEquals("Initial Title", savedBook.getTitle());
        assertEquals(Genre.HORROR, savedBook.getGenre());
        assertEquals(200, savedBook.getPages());
    }

    @Test
    void testUpdate() {
        Book savedBook = new Book(1, "Initial Title", Genre.THRILLER, 200);

        bookBusiness.update(savedBook, "New Title", Genre.SCIENCE_FICTIONAL, 900);

        assertEquals("New Title", savedBook.getTitle());
        assertEquals(Genre.SCIENCE_FICTIONAL, savedBook.getGenre());
        assertEquals(900, savedBook.getPages());
    }

    @Test
    void testRemoveAuthor_NullAuthors() {
        assertThrows(IllegalArgumentException.class, () -> bookBusiness.removeAuthor(null, 123));
    }

    @Test
    void testReveAuthor_EmptyAuthors() {
        assertThrows(IllegalArgumentException.class, () -> bookBusiness.removeAuthor(Collections.emptyList(), 123));
    }

    @Test
    void testRemoveAuthor_SingleAuthor() {
        List<Author> authors = new ArrayList<>();

        Author author = new Author();
        author.setName("John Doe");

        authors.add(author);

        try {
            bookBusiness.removeAuthor(authors, 123);
        } catch (ValidationsException exception) {
            assertEquals(MessageUtil.getMessage("0100", "123"), exception.getMessage());
        }
    }

    @Test
    void testCreate_NullAuthors() {
        try {
            bookBusiness.create(null, new ArrayList<>());
        } catch (ValidationsException exception) {
            assertEquals(MessageUtil.getMessage("0001", "Autor"), exception.getMessage());
        }
    }

    @Test
    void testCreate_NullPublishers() {
        try {
            bookBusiness.create(List.of(new Author()), null);
        } catch (ValidationsException exception) {
            assertEquals(MessageUtil.getMessage("0001", "Editora"), exception.getMessage());
        }
    }

    @Test
    void testRemovePublisher_NullPublishers() throws ValidationsException{
        try {
            bookBusiness.removePublisher(null, 123);
        } catch (IllegalArgumentException exception) {
            assertEquals(MessageUtil.getMessage("0003"), exception.getMessage());
        }
    }

    @Test
    void testRemovePublisher_SinglePublisher() {
        try {
            bookBusiness.removePublisher(List.of(new Publisher()), 123);
        } catch (ValidationsException exception) {
            assertEquals(MessageUtil.getMessage("0301", "123"), exception.getMessage());
        }
    }
}
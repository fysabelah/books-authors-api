package com.store.bookshelf.usercase;

import com.store.bookshelf.entities.Author;
import com.store.bookshelf.entities.Book;
import com.store.bookshelf.util.MessageUtil;
import com.store.bookshelf.util.exceptions.ValidationsException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AuthorBusinessTest {

    private static AuthorBusiness business;

    @BeforeAll
    public static void setUp() {
        business = new AuthorBusiness();
    }

    @Test
    void testDeleteAuthorWithBooks() {
        Author author = new Author();
        author.setName("John Doe");
        author.setBirthdate(LocalDate.now());

        List<Book> books = new ArrayList<>();
        books.add(new Book());

        author.setBooks(books);

        try {
            business.delete(author);
        } catch (ValidationsException e) {
            assertEquals(e.getMessage(), MessageUtil.getMessage("0200"));
        }
    }

    @Test
    void testDeleteAuthorWithNoBooks() {
        Author author = new Author();
        author.setName("John Doe");
        author.setBirthdate(LocalDate.now());

        assertDoesNotThrow(() -> business.delete(author));
    }

    @Test
    void testUpdateName() {
        Author savedAuthor = new Author("John Doe", LocalDate.of(1990, 5, 15));
        String newName = "Jane Smith";

        Author updatedAuthor = business.update(savedAuthor, newName, null);

        assertEquals(newName, updatedAuthor.getName());
        assertEquals(savedAuthor.getBirthdate(), updatedAuthor.getBirthdate());
    }

    @Test
    void testUpdateBirthdate() {
        Author savedAuthor = new Author("John Doe", LocalDate.of(1990, 5, 15));
        String newBirthdate = "2000-10-20";
        LocalDate expectedDate = LocalDate.parse(newBirthdate, DateTimeFormatter.ISO_LOCAL_DATE);

        Author updatedAuthor = business.update(savedAuthor, null, newBirthdate);

        assertEquals(savedAuthor.getName(), updatedAuthor.getName());
        assertEquals(expectedDate, updatedAuthor.getBirthdate());
    }

    @Test
    void testUpdateWithNullParameters() {
        Author savedAuthor = new Author("John Doe", LocalDate.of(1990, 5, 15));

        try {
            business.update(savedAuthor, null, null);
        } catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(), MessageUtil.getMessage("0003"));
        }
    }

    @Test
    void testUpdateWithInvalidBirthdate() {
        Author savedAuthor = new Author("John Doe", LocalDate.of(1990, 5, 15));
        String invalidBirthdate = "2020-20-50";

        try {
            business.update(savedAuthor, null, invalidBirthdate);
        } catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(), MessageUtil.getMessage("0004"));
        }
    }
}
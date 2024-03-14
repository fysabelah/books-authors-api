package com.store.bookshelf.usercase;

import com.store.bookshelf.entities.Author;
import com.store.bookshelf.entities.Book;
import com.store.bookshelf.util.MessageUtil;
import com.store.bookshelf.util.enums.Genre;
import com.store.bookshelf.util.exceptions.ValidationsException;

import java.util.List;

public class BookBusiness {

    public Book update(Book saved, String title, Genre genre, Integer pages) throws ValidationsException {
        if (title != null && !title.trim().isEmpty()) {
            saved.setTitle(title);
        }

        if (genre != null) {
            saved.setGenre(genre);
        }

        if (pages != null && pages > 0) {
            saved.setPages(pages);
        }

        return saved;
    }

    public Book removeAuthor(Book book, List<Author> authors, Author author) throws ValidationsException {
        if (book == null || author == null || authors == null || authors.isEmpty()) {
            throw new IllegalArgumentException(MessageUtil.getMessage("0003"));
        }

        authors.remove(author);

        if (authors.isEmpty()) {
            throw new ValidationsException("0101");
        }

        book.setAuthors(authors);

        return book;
    }

    public Book addAuthors(Book book, Author author) {
        book.getAuthors().add(author);

        return book;
    }
}

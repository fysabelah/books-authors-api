package com.store.bookshelf.usercase;

import com.store.bookshelf.entities.Author;
import com.store.bookshelf.entities.Book;
import com.store.bookshelf.entities.Publisher;
import com.store.bookshelf.util.MessageUtil;
import com.store.bookshelf.util.enums.Genre;
import com.store.bookshelf.util.exceptions.ValidationsException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookBusiness {

    public void update(Book saved, String title, Genre genre, Integer pages) {
        if (title != null && !title.trim().isEmpty()) {
            saved.setTitle(title);
        }

        if (genre != null) {
            saved.setGenre(genre);
        }

        if (pages != null && pages > 0) {
            saved.setPages(pages);
        }
    }

    public void removeAuthor(List<Author> authors, Integer bookId) throws ValidationsException {
        if (authors == null || authors.isEmpty()) {
            throw new IllegalArgumentException(MessageUtil.getMessage("0003"));
        }

        if (authors.size() == 1) {
            throw new ValidationsException("0100", bookId.toString());
        }
    }

    public void create(List<Author> authors, List<Publisher> publishers) throws ValidationsException {
        if (authors == null || authors.isEmpty()) {
            throw new ValidationsException("0001", "Autor");
        }

        if (publishers == null || publishers.isEmpty()) {
            throw new ValidationsException("0001", "Editora");
        }
    }

    public void removePublisher(List<Publisher> publishers, Integer bookId) throws ValidationsException {
        if (publishers == null || publishers.isEmpty()) {
            throw new IllegalArgumentException(MessageUtil.getMessage("0003"));
        }

        if (publishers.size() == 1) {
            throw new ValidationsException("0301", bookId.toString());
        }
    }
}

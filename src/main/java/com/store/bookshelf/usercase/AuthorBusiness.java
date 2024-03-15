package com.store.bookshelf.usercase;

import com.store.bookshelf.entities.Author;
import com.store.bookshelf.entities.Book;
import com.store.bookshelf.util.MessageUtil;
import com.store.bookshelf.util.exceptions.ValidationsException;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

@Component
public class AuthorBusiness {

    public void delete(Author author, List<Book> books) throws ValidationsException {
        if (author == null) {
            throw new IllegalArgumentException(MessageUtil.getMessage("0002", "Autor"));
        }

        if (books != null && !books.isEmpty()) {
            throw new ValidationsException("0201");
        }
    }

    public Author update(Author saved, String name, String birthdate) {
        if (name == null && birthdate == null) {
            throw new IllegalArgumentException(MessageUtil.getMessage("0003"));
        }

        if (name != null) {
            saved.setName(name);
        }

        if (birthdate != null) {
            LocalDate date;

            try {
                date = LocalDate.parse(birthdate, DateTimeFormatter.ISO_LOCAL_DATE);
                saved.setBirthdate(date);
            } catch (DateTimeParseException exception) {
                throw new IllegalArgumentException(MessageUtil.getMessage("0004"));
            }
        }

        return saved;
    }
}

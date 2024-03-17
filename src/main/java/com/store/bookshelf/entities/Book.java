package com.store.bookshelf.entities;

import com.store.bookshelf.util.MessageUtil;
import com.store.bookshelf.util.enums.Genre;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Entity(name = "Book")
@Table(name = "book")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Book implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", nullable = false)
    private String title;

    @Enumerated(EnumType.STRING)
    private Genre genre;

    @Column(name = "pages", nullable = false)
    private Integer pages;

    @ManyToMany
    @JoinTable(name = "authors_books", joinColumns = @JoinColumn(name = "author_id"), inverseJoinColumns = @JoinColumn(name = "book_id"))
    private List<Author> authors;

    @ManyToMany
    @JoinTable(name = "publisher_books", joinColumns = @JoinColumn(name = "publisher_id"), inverseJoinColumns = @JoinColumn(name = "book_id"))
    private List<Publisher> publishers;

    public void setTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException(MessageUtil.getMessage("0001", "Título"));
        }

        this.title = title;
    }

    public void setGenre(Genre genre) {
        if (genre == null) {
            throw new IllegalArgumentException(MessageUtil.getMessage("0001", "G\u00EAnero"));
        }

        this.genre = genre;
    }

    public void setPages(Integer pages) {
        if (pages == null || pages <= 0) {
            throw new IllegalArgumentException(MessageUtil.getMessage("0001", "P\u00E1gina"));
        }

        this.pages = pages;
    }

    public void setAuthors(List<Author> authors) {
        if (authors == null || authors.isEmpty()) {
            throw new IllegalArgumentException(MessageUtil.getMessage("0001", "Autor"));
        }

        this.authors = authors;
    }
}

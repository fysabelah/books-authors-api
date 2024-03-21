package com.store.bookshelf.entities;

import com.store.bookshelf.util.MessageUtil;
import com.store.bookshelf.util.enums.Genre;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity(name = "Book")
@Table(name = "book")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Book implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Setter
    private Integer id;

    @Column(name = "name", nullable = false)
    private String title;

    @Enumerated(EnumType.STRING)
    private Genre genre;

    @Column(name = "pages", nullable = false)
    private Integer pages;

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
}

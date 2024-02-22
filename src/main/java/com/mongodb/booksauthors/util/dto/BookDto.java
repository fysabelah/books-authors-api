package com.mongodb.booksauthors.util.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class BookDto extends Dto {

    @NotEmpty
    private String title;

    @NotEmpty
    private String genre;

    @Positive
    private Integer pages;

    public BookDto(String id, String title, String genre, Integer pages) {
        super(id);
        this.title = title;
        this.genre = genre;
        this.pages = pages;
    }
}

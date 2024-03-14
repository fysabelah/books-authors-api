package com.store.bookshelf.interfaceadapters.presenter.dto;

import com.store.bookshelf.util.enums.Genre;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class BookDto extends Dto {

    @NotEmpty
    @Schema(example = "The Little Prince")
    private String title;

    @NotNull
    private Genre genre;

    @Positive
    private Integer pages;


    @NotEmpty
    private List<AuthorDto> authors;

    public BookDto(Integer id, String title, Genre genre, Integer pages, List<AuthorDto> authors) {
        super(id);
        this.title = title;
        this.genre = genre;
        this.pages = pages;
        this.authors = authors;
    }
}

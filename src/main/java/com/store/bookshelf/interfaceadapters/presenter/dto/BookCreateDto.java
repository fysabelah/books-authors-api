package com.store.bookshelf.interfaceadapters.presenter.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class BookCreateDto extends BookDto {

    @NotEmpty
    private List<Integer> authors;

    @NotEmpty
    private List<Integer> publishers;
}

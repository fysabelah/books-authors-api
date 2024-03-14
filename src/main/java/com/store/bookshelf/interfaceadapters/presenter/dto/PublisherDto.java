package com.store.bookshelf.interfaceadapters.presenter.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PublisherDto extends Dto {

    @NotEmpty
    @Schema(example = "Rocco")
    private String name;

    private List<BookDto> books;
}

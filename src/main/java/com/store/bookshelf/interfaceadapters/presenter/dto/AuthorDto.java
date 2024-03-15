package com.store.bookshelf.interfaceadapters.presenter.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
public class AuthorDto extends Dto {

    @NotEmpty
    @Schema(example = "George Martin")
    private String name;

    @NotEmpty
    @Pattern(regexp = "[0-9]{4}-[0-9]{2}-[0-9]{2}")
    @Schema(example = "1997-02-14")
    private String birthdate;

    public AuthorDto(Integer id, String name, LocalDate birthdate) {
        super(id);
        this.name = name;
        this.birthdate = birthdate.toString();
    }
}

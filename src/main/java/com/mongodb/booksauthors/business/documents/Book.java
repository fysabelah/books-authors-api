package com.mongodb.booksauthors.business.documents;

import com.mongodb.booksauthors.util.enums.Genre;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

@Document("books")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Book implements Serializable {

    @Id
    private String id;

    private String title;

    private Genre genre;

    private Integer pages;

    private List<Author> authors;
}

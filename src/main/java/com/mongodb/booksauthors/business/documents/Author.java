package com.mongodb.booksauthors.business.documents;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Document("authors")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Author implements Serializable {

    @Id
    private String id;

    private String name;

    private LocalDate birthdate;

    @DBRef
    private List<Book> books;

    public Author(String id, String name, LocalDate birthdate) {
        this.id = id;
        this.name = name;
        this.birthdate = birthdate;
    }
}

package com.mongodb.booksauthors.business.documents;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

@Document("publishers")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Publisher implements Serializable {

    @Id
    private String id;

    private String name;

    @DBRef
    private List<Book> books;
}

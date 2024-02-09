package com.mongodb.booksauthors.business.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("books")
public class Book {

    @Id
    private String id;

    private String title;

    private String genre;

    private Integer pages;
}

package com.store.bookshelf.entities;

import com.store.bookshelf.util.MessageUtil;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@Entity(name = "Author")
@Table(name = "author")
public class Author implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "birthdate", columnDefinition = "DATE")
    private LocalDate birthdate;

    public Author(Integer id, String name, LocalDate birthdate) {
        this.id = id;
        this.setName(name);
        this.setBirthdate(birthdate);
    }

    public Author(String name, LocalDate date) {
        this.setName(name);
        this.setBirthdate(date);
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException(MessageUtil.getMessage("0001", "Nome"));
        }

        this.name = name;
    }

    public void setBirthdate(LocalDate birthdate) {
        if (birthdate == null) {
            throw new IllegalArgumentException(MessageUtil.getMessage("0001", "Data de nascimento"));
        }

        this.birthdate = birthdate;
    }
}

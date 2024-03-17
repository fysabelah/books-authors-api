package com.store.bookshelf.entities;

import com.store.bookshelf.util.MessageUtil;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity(name = "Publisher")
@Table(name = "publisher")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Publisher implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException(MessageUtil.getMessage("0001", "Nome"));
        }

        this.name = name;
    }
}

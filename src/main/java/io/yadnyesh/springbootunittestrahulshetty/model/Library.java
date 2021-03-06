package io.yadnyesh.springbootunittestrahulshetty.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Table(name="storage2")
public class Library {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "book_name")
    private String book_name;

    @Column(name = "isbn")
    private String isbn;

    @Column(name = "aisle")
    private int aisle;

    @Column(name = "author")
    private String author;
}

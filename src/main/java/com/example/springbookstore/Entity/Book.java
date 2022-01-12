package com.example.springbookstore.Entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "Book", indexes = {
        @Index(name = "idx_book_isbn", columnList = "ISBN")
})
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String ISBN;
    private String BookName;
    private String AuthorName;
    private String BookImg;
    private Date PublishDate;
}

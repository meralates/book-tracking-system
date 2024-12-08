package com.example.booktrackingsystem.model;

import jakarta.persistence.*;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String publisher;
    private String publishingDate;

    @ManyToOne
    @JoinColumn(name="author_id",nullable = false)
    private Author author;

    public Book() {}//bos constructor
    public Book(String name, Author author, String publisher, String publishingDate) {
        this.name = name;
        this.author=author;
        this.publisher = publisher;
        this.publishingDate = publishingDate;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Author getAuthor() {
        return author;
    }
    public void setAuthor(Author author) {
        this.author = author;
    }
    public String getPublisher() {
        return publisher;
    }
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
    public String getPublishingDate() {
        return publishingDate;
    }
    public void setPublishingDate(String publishingDate) {
        this.publishingDate = publishingDate;
    }

}

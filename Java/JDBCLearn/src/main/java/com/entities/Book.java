package com.entities;

import java.util.Objects;

public class Book {
    private String isbn;
    private String title;
    private String description;
    private Integer authorId;
    private Author author;

    public Book(){}
    public Book(String isbn, String title, String description, Integer authorId) {
        this.isbn = isbn;
        this.title = title;
        this.description = description;
        this.authorId = authorId;
    }

    public String getIsbn() {
        return isbn;
    }
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getAuthorId() {
        return authorId;
    }
    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public Author getAuthor() {
        return author;
    }
    public void setAuthor(Author author) {
        this.author = author;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(getIsbn(), book.getIsbn()) && Objects.equals(getTitle(), book.getTitle()) && Objects.equals(getDescription(), book.getDescription()) && Objects.equals(getAuthorId(), book.getAuthorId()) && Objects.equals(getAuthor(), book.getAuthor());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIsbn(), getTitle(), getDescription(), getAuthorId(), getAuthor());
    }

    @Override
    public String toString() {
        return "Book{" +
                "isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", authorId=" + authorId +
                ", author=" + author +
                '}';
    }
}

package org.example.model;

public class Book {
    private final String bookName;
    private final String bookAuthor;
    private final String bookGenre;
    private final boolean isBorrowed;

    public Book(String bookName, String bookAuthor, String bookGenre, boolean isBorrowed) {
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.bookGenre = bookGenre;
        this.isBorrowed = isBorrowed;
    }

    public String getBookName() {
        return bookName;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public String getBookGenre() {
        return bookGenre;
    }

    public boolean isBorrowed() {
        return isBorrowed;
    }
}

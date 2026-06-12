package org.example.model;

public class Book {
    private final String bookName;
    private final  String bookAuthor;
    private final  String bookGenre;
    private boolean isBorrowed = false;

    public Book(String bookName, String bookAuthor, String bookGenre) {
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.bookGenre = bookGenre;

    }

    public void setBorrowed(boolean borrowed) {
        isBorrowed = borrowed;
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

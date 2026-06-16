package org.example.model;

public class RequestObject {
    private final String requesterName;
    private final String date;
    private final String bookName;
    private final String bookGenre;

    public RequestObject(String requesterName, String date, String bookName, String bookGenre) {
        this.requesterName = requesterName;
        this.date = date;
        this.bookName = bookName;
        this.bookGenre = bookGenre;
    }

    public String getRequesterName() {
        return requesterName;
    }

    public String getDate() {
        return date;
    }

    public String getBookName() {
        return bookName;
    }

    public String getBookGenre() {
        return bookGenre;
    }
}

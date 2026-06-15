package org.example.model;

public class RequestObject {
    private String name;
    private String date;
    private String book;

    public RequestObject(String name, String date, String book) {
        this.name = name;
        this.date = date;
        this.book = book;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getBook() {
        return book;
    }
}

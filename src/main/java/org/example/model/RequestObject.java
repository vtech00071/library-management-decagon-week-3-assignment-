package org.example.model;

public class RequestObject {

    private final String bookName;
    private final String bookGenre;
    private final String requesterEmail;
    private final int priority;
    private int arrivalCounter;

    public RequestObject(String bookName, String bookGenre, String requesterEmail, int priority,int arrivalCounter) {
        this.bookName = bookName;
        this.bookGenre = bookGenre;
        this.requesterEmail = requesterEmail;
        this.priority = priority;
        this.arrivalCounter = arrivalCounter;

    }

    public int getArrivalCounter() {
        return arrivalCounter;
    }

    public String getRequesterEmail() {
        return requesterEmail;
    }

    public int getPriority() {
        return priority;
    }

    public String getBookName() {
        return bookName;
    }

    public String getBookGenre() {
        return bookGenre;
    }
}

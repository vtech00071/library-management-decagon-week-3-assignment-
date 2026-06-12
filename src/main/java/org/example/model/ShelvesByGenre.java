package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class ShelvesByGenre {
    //the reason why i used final is that i don't want it to be declared anywhere again
    private final List<Book> books ;
    private final  String genre;
    private final  int shelfNumber;

    public ShelvesByGenre( String genre, int shelfNumber) {
        this.books = new ArrayList<>();
        this.genre = genre;
        this.shelfNumber = shelfNumber;
    }

    public List<Book> getBooks() {
        return books;
    }

    public String getGenre() {
        return genre;
    }

    public int getShelfNumber() {
        return shelfNumber;
    }
}

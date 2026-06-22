package org.example.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LibraryTest {

    Library library = new Library();

    @Test
    void thisshouldreturnfalseifthebookdoesnotexist() {

        //arrange
        String requesterEmail = "victorfemi@gmail.com";
        String bookName = "dune";
        String bookGenre = "science fiction";
        String studentOrTeacher = "student";

        //act
       library.requestBook(requesterEmail,bookName,bookGenre,studentOrTeacher);
        assertNull();





    }

    @Test
    void addRequestBook() {
    }

    @Test
    void serveBook() {
    }

    @Test
    void loginAccount() {
    }

    @Test
    void loginLibrarian() {
    }

    @Test
    void loginTeachersAccount() {
    }

    @Test
    void createAccount() {
    }

    @Test
    void borrowBook() {
    }
}
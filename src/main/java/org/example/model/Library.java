package org.example.model;

import org.example.enums.AccountCreationMessage;
import org.example.enums.LoginMessage;
import org.example.enums.RequestBookOutcome;
import org.example.util.CreateAccountServices;
import org.example.util.LoginServices;

import java.util.*;
//import java.util.
import java.util.function.Predicate;

import static java.util.Arrays.stream;
import static org.example.enums.AccountCreationMessage.*;
import static org.example.enums.LoginMessage.*;
import static org.example.enums.RequestBookOutcome.*;

public class Library implements LoginServices, CreateAccountServices {
    private final Map<String, Librarian> librarians;
    private final Map<String, Person> libraryUsers;
    private final Map<String, ShelvesByGenre> shelves;
    static int arrivalCounter = 0;

    // this is a priority queue and we are using lambda expressions
    private final Queue<RequestObject> requestBook = new PriorityQueue<>((a, b) -> {
        if (a.getPriority() != b.getPriority()) {
            return Integer.compare(a.getPriority(), b.getPriority());
        }
        //here we using arrival counter intead and that is when
        return Integer.compare(a.getArrivalCounter(), b.getArrivalCounter());
    });


    public Library() {
        this.librarians = new HashMap<>();
        this.shelves = new HashMap<>();
        this.libraryUsers = new HashMap<>();
    }

    public Map<String, Librarian> getLibrarians() {
        return librarians;
    }

    public Map<String, Person> getLibraryUsers() {
        return libraryUsers;
    }

    public Map<String, ShelvesByGenre> getShelves() {
        return shelves;
    }

    public static int getArrivalCounter() {
        return arrivalCounter;
    }

    public Queue<RequestObject> getRequestBook() {
        return requestBook;
    }

    //this method will check if the book that is requested exists
    public RequestBookOutcome requestBook(String bookName, String bookGenre) {
        ShelvesByGenre targetShelf = this.shelves.get(bookGenre);
        if (targetShelf == null) {
            return GENRE_NOT_FOUND;
        }
        for (Book theBookName : this.shelves.get(bookGenre).getBooks()) {
            if (theBookName.getBookName().equals(bookName)) {
                if (theBookName.getIsBorrowed()) {
                    return BOOK_HAS_BEEN_BORROWED;
                } else {
                    return BOOK_REQUESTED_SUCCESSFULLY;
                }
            }
        }
        return BOOK_DOES_NOT_EXIST;
    }


    //this is the method that we will use to serve the book
    //
    public void serveBook() {
        while (!this.requestBook.isEmpty()) {
            //this means the books based on their priority
            //i cant use declarative approach for this because it contains business logics and so many condition and also
            //change of state of some properties so it will be a bad idea for me to use declarative approach for this
            RequestObject theBook = this.requestBook.poll();
            for (Book book : this.shelves.get(theBook.getBookGenre()).getBooks()) {
                //all the book will set their borrowed to true
                if (book.getBookName().equals(theBook.getBookName())) {
                    book.setBorrowed(true);
                    String bookNames = theBook.getBookName();
                    String requesterUsername = (this.libraryUsers.get(theBook.getRequesterEmail()).getFirstname() + " " + this.libraryUsers.get(theBook.getRequesterEmail()).lastname);
                    String requesterIdentity = this.libraryUsers.get(theBook.getRequesterEmail()).identity;
                    if (requesterIdentity.equals("student")) {
                        String requesterLevel = this.libraryUsers.get(theBook.getRequesterEmail()).level;
                        System.out.println(bookNames + " has been served successfully to NAME:  " + requesterUsername + " IDENTITY: " + requesterIdentity + " LEVEL: " + requesterLevel);
                    } else {
                        System.out.println(bookNames + " has been served successfully to NAME:  " + requesterUsername + " IDENTITY: " + requesterIdentity);
                    }
                }
            }
        }
        System.out.println("this book was not served successfully");
    }

    //this is the method for login students
    @Override
    public LoginMessage loginAccount(String email, String password) {
        if (!this.libraryUsers.containsKey(email)) {
            return USER_DOES_NOT_EXIST;
        }
        if (!this.libraryUsers.get(email).getPassword().equals(password)) {
            return INVALID_PASSWORD;
        }
        return LOGIN_SUCCESSFUL;
    }

    //this method is for librarian login
    public LoginMessage loginLibrarian(String email, String password) {
        if (!this.librarians.containsKey(email)) {
            return USER_DOES_NOT_EXIST;
        }
        if (!this.librarians.get(email).getPassword().equals(password)) {
            return INVALID_PASSWORD;
        }
        return LOGIN_SUCCESSFUL;
    }


    //this is the account creation logic
    @Override
    public AccountCreationMessage createAccount(Map<String, String> userFields) {
        List<String> correctTitles = new ArrayList<>(Arrays.asList("mr", "mrs", "prof", "dr"));
        String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        //this will go through all the value and check if any value is empty
        if (userFields.containsValue(null)) {
            return EMPTY_FIELDS;
        }
        //check if the email is a valid email
        if (!userFields.get("email").matches(regex)) {
            return INVALID_EMAIL;
        }
        //check if the password and the confirmation password is the same
        if (!userFields.get("password").equals(userFields.get("confirmPassword"))) {
            return PASSWORDS_DOES_NOT_MATCH;
        }
        //check if the title is a valid title
        if (!correctTitles.contains(userFields.get("title"))) {
            return INVALID_TITLE;
        }
        return ACCOUNT_CREATION_SUCCESSFUL;
    }

}

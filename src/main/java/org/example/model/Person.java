package org.example.model;

import org.example.util.CreateAccountServices;
import org.example.util.LoginServices;

//this is an abstract class because I don't want to instantiate it
abstract public class Person implements CreateAccountServices {
    protected String firstname;
    protected String lastname;
    protected String email;
    protected String password;
    protected String title;
    protected int libraryId;
    protected String identity;

    public Person(String firstname, String lastname, String email, String password, String title, int libraryId,String identity) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.title = title;
        this.libraryId = libraryId;
        this.identity = identity;
    }

    public String getIdentity() {
        return identity;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getTitle() {
        return title;
    }

    public int getLibraryId() {
        return libraryId;
    }
}

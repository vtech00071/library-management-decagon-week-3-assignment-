package org.example.model;

import org.example.util.CreateAccountServices;
import org.example.util.LoginServices;

//this is an abstract class because I don't want to instantiate it
abstract public class Person implements CreateAccountServices {
    protected String firstname;
    protected String lastname;
    protected String email;
    protected String password;
//    protected String confirmPassword;
    protected String title;
    //library id will be generated after the creation of account,
    //but I will still declare it as a properties of this field
    protected int libraryId;

    public Person(String firstname, String lastname, String email, String password, String title, int libraryId) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.title = title;
        this.libraryId = libraryId;
    }

    //
//    public void setFirstname(String firstname) {
//        this.firstname = firstname;
//    }
//
//    public void setLastname(String lastname) {
//        this.lastname = lastname;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public void setLibraryId(int libraryId) {
//        this.libraryId = libraryId;
//    }
    //this is the getter with this getter that means we can use this in a different class

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

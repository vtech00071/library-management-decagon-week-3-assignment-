package org.example.model;

import org.example.util.LoginServices;

import java.util.*;

public class Library implements LoginServices {

    Set<Librarian> librarians = new HashSet<>();
    Map<Integer, Person> students = new HashMap<>();
    List<Book> books = new ArrayList<>();
    Map<String, Person> teacher = new HashMap<>();

    public Library() {
        this.librarians = new HashSet<>();
        //this hashmap will contain student objects and library id
        this.students = new HashMap<>();
        this.books = new ArrayList<>();
        this.teacher = new HashMap<>();
    }

    public Set<Librarian> getLibrarians() {
        return librarians;
    }

    public Map<Integer, Person> getStudents() {
        return students;
    }

    public List<Book> getBooks() {
        return books;
    }

    public Map<String, Person> getTeacher() {
        return teacher;
    }

    //this is the method for login students
    @Override
    public boolean loginStudentAccount(int libraryId, String password) {
        boolean userExists = this.getStudents().containsKey(libraryId);
        //this will check if the password is equals to the password of the object
        // I use try catch here because of null pointer exception
        try {
            if (!userExists) {
                System.out.println("USER NOT FOUND XXX");
                return false;
            }
            if (this.getStudents().get(libraryId).getPassword().equals(password)) {
                System.out.println("login is successfull");
                return true;
            } else {
                System.out.println("invalid password ");
                return false;
            }
        } catch (NullPointerException _) {
            System.out.println("USER NOT FOUND XXX");
            return false;
        }
    }
}

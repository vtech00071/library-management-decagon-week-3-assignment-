package org.example.model;

import java.util.Map;

public class Librarian extends Person {
    private String staffId;

    public Librarian(String firstname, String lastname, String email, String password, String title, int libraryId, String identity) {
        super(firstname, lastname, email, password, title, libraryId, identity);
    }

    @Override
    public boolean createAccount(Map<String, String> userFields) {
        return false;
    }
}

package org.example.model;

import java.util.Map;

public class Teachers extends Person{
    public Teachers(String firstname, String lastname, String email, String password, String title, int libraryId) {
        super(firstname, lastname, email, password, title, libraryId);
    }

    @Override
    public boolean createAccount(Map<String, String> userFields) {
        return false;
    }
//
//    @Override
//    public boolean loginAccount(String libraryId, String password) {
//        return false;
//    }
}

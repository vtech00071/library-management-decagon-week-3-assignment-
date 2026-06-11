package org.example.model;

import java.util.Map;

public class Librarian extends Person {
    private String staffId;

    @Override
    public boolean createAccount(Map<String, String> userFields) {
        return false;
    }
}

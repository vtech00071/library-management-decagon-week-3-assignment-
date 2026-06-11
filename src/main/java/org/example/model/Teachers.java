package org.example.model;

import java.util.Map;

public class Teachers extends Person{
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

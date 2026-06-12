package org.example.model;

import java.util.*;

public class Students extends Person {
    private String matricNumber;
    private String level;
    private String department;

    public Students(String firstname, String lastname, String email, String password, String title, int libraryId) {
        super(firstname, lastname, email, password, title, libraryId);
    }

    //i must still check whether the matric number is a valid one
    public void setMatricNumber(String matricNumber) {
        this.matricNumber = matricNumber;
    }

    // i must still check if the level is a valid one
    public void setLevel(String level) {
        this.level = level;
    }

    //  i must still check whether the department is a valid one
    public void setDepartment(String department) {
        this.department = department;
    }

    public String getMatricNumber() {
        return matricNumber;
    }

    public String getLevel() {
        return level;
    }

    public String getDepartment() {
        return department;
    }

    @Override
    public boolean createAccount(Map<String, String> userFields) {
        return false;
    }

    //this method is a account creation method

}

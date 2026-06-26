package org.example.model;

import org.example.enums.AccountCreationMessage;

import java.util.*;

public class Students extends Person {
    private String matricNumber;
    private String department;

    public Students(String firstname, String lastname, String email, String password, String title, int libraryId, String identity) {
        super(firstname, lastname, email, password, title, libraryId, identity);
    }

    //i must still check whether the matric number is a valid one
    public void setMatricNumber(String matricNumber) {
        this.matricNumber = matricNumber;
    }

    //  i must still check whether the department is a valid one
    public void setDepartment(String department) {
        this.department = department;
    }

    public String getMatricNumber() {
        return matricNumber;
    }


    public String getDepartment() {
        return department;
    }

}

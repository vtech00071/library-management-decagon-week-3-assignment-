package org.example.model;

import java.util.*;

public class Students extends Person {
    private String matricNumber;
    private String level;
    private String department;

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

    //this method is a account creation method
    @Override
    public boolean createAccount(Map<String, String> userFields) {
//            List<String> correctTitles = new ArrayList<>(List.of("mr", "mrs", "prof", "dr"));
//        int validTitle = Collections.binarySearch(correctTitles,userFields.get("title"));
//        String  val = correctTitles.get(validTitle);
        List<String> correctTitles = new ArrayList<>();
        correctTitles.add("mr");
        correctTitles.add("mrs");
        correctTitles.add("prof");
        correctTitles.add("dr");
        String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        boolean checkNull = false;
        for (String values : userFields.values()) {
            if (values == null) {
                checkNull = true;
                break;
            }
        }
        //check if values are not null
        if (checkNull) {
            System.out.println("the values of this fields are null");
            return false;
        }
        //check if the email is a valid email
        if (!userFields.get("email").matches(regex)) {
            System.out.println("this email is invalid");
            return false;
        }

        //check if the password and the confirmation password is the same
        if (!userFields.get("password").equals(userFields.get("confirmPassword"))) {
            System.out.println("your password does not match please try again");
            return false;
        }
        //check if the title is a valid title
        if (!correctTitles.contains(userFields.get("title"))) {
            System.out.println("this title does not exist");
            return false;
        }
        //if all the conditions are passed then the account creation will be successfull
        else {
            System.out.println("account creation success!!!");
            return true;
        }

    }

}

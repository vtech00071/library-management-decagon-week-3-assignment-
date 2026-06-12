package org.example.model;

import org.example.util.CreateAccountServices;
import org.example.util.LoginServices;

import java.util.*;

public class Library implements LoginServices , CreateAccountServices {

    private Set<Librarian> librarians;
    private Map<Integer, Person> students;
    private List<Book> books;
    private Map<String, Person> teacher;

    //this is a constructor
    public Library() {
        this.librarians = new HashSet<>();
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
        boolean userExist = this.students.containsKey(libraryId);
        try {
            if (!userExist) {
                System.out.println("USER NOT FOUND !!!");
                return false;
            }
            //this either return true of false
            return this.students.get(libraryId).getPassword().equals(password);
        } catch (NullPointerException e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
            return false;
        }
    }
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

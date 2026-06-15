package org.example.model;

import org.example.util.CreateAccountServices;
import org.example.util.LoginServices;
import java.util.*;

public class Library implements LoginServices, CreateAccountServices {

    //all this is compsotion i want it to has a
    private final Set<Librarian> librarians;
    private final Map<String, Students> students;
    private final List<Book> books;
    private final Map<String, Teachers> teacher;
    private final Map<String, ShelvesByGenre> shelves;
    private final Queue<RequestObject> requestBook;

    //this is a constructor
    public Library() {
        this.librarians = new HashSet<>();
        this.students = new HashMap<>();
        this.books = new ArrayList<>();
        this.teacher = new HashMap<>();
        this.shelves = new HashMap<>();
        this.requestBook = new LinkedList<>();
    }

    // this are the getters with this getters we can now access all of this properties outside this class
    public Map<String, ShelvesByGenre> getShelves() {
        return shelves;
    }

    public Set<Librarian> getLibrarians() {
        return librarians;
    }

    public Map<String, Students> getStudents() {
        return students;
    }

    public List<Book> getBooks() {
        return books;
    }

    public Map<String, Teachers> getTeacher() {
        return teacher;
    }

    //this will allow will allow us to get the this queue and use it in the main function
    public Queue<RequestObject> getRequestBook() {
        return requestBook;
    }
    //how many times does a book name apear in a object
    public boolean requestBook(String bookName, String bookGenre) {
        boolean genreExist = this.shelves.containsKey(bookGenre);
        boolean bookNameExist = false;
        boolean bookBorrowed = false;

        for (Book theBookName : this.shelves.get(bookGenre).getBooks()) {
            if (theBookName.getBookName().equals(bookName)) {
                bookNameExist = true;
                if (theBookName.getIsBorrowed()) {
                    bookBorrowed = true;
                } else {
                    theBookName.setBorrowed(true);
                    System.out.println(bookName + " " + bookGenre + " has been borrowed successfully");
                }
                break;
            }
        }
        // i used all this one because of the if else statement
        if (!genreExist) {
            System.out.println("this book genre does not exist");
            return false;
        }
        if (!bookNameExist) {
            System.out.println("this book does not exist");
            return false;
        }
        if (bookBorrowed) {
            System.out.println("this book has been taken you cannot borrow it ");
            return false;
        } else {
            return true;
        }
    }

    //this is the method for login students
    @Override
    public boolean loginStudentAccount(String email, String password) {
        boolean userExist = this.students.containsKey(email);
        try {
            if (!userExist) {
                System.out.println("USER NOT FOUND !!!");
                return false;
            }
            //this either return true of false
            return this.students.get(email).getPassword().equals(password);
            // we have this null pointer because what i if the email which is the key is null
        } catch (NullPointerException e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
            return false;
        }
    }

    //this method will work for the teachers login
    @Override
    public boolean loginTeachersAccount(String email, String password) {
        boolean userExist = this.teacher.containsKey(email);
        try {
            if (!userExist) {
                System.out.println("USER NOT FOUND !!!");
                return false;
            }
            //this either return true of false
            return this.teacher.get(email).getPassword().equals(password);
            // we have this null pointer because what i if the email which is the key is null
        } catch (NullPointerException e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
            return false;
        }
    }

    @Override
    public boolean createAccount(Map<String, String> userFields) {
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
//                break;
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

    public boolean borrowBook(String bookName, String bookGenre) {
        boolean genreExist = this.shelves.containsKey(bookGenre);
        boolean bookNameExist = false;
        boolean bookBorrowed = false;

        for (Book theBookName : this.shelves.get(bookGenre).getBooks()) {
            if (theBookName.getBookName().equals(bookName)) {
                bookNameExist = true;
                if (theBookName.getIsBorrowed()) {
                    bookBorrowed = true;
                } else {
                    theBookName.setBorrowed(true);
                    System.out.println(bookName + " " + bookGenre + " has been borrowed successfully");
                }
                break;
            }
        }
        // i used all this one because of the if else statement
        if (!genreExist) {
            System.out.println("this book genre does not exist");
            return false;
        }
        if (!bookNameExist) {
            System.out.println("this book does not exist");
            return false;
        }
        if (bookBorrowed) {
            System.out.println("this book has been taken you cannot borrow it ");
            return false;
        } else {
            return true;
        }
    }



}

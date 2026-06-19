package org.example.model;

import org.example.util.CreateAccountServices;
import org.example.util.LoginServices;

import java.util.*;

public class Library implements LoginServices, CreateAccountServices {
    private final Map<String, Librarian> librarians;
    private final Map<String, Students> students;
    private final List<Book> books;
    private final Map<String, Teachers> teacher;
    private final Map<String, ShelvesByGenre> shelves;
    static int arrivalCounter = 0;
    private final PriorityQueue<RequestObject> requestBook = new PriorityQueue<>(
            (a, b) -> {
                if (a.getPriority() != b.getPriority()) {
                    return a.getPriority() - b.getPriority();
                } else {
                    return a.getArrivalCounter() - b.getArrivalCounter();
                }
            }
    );


    //this is a constructor
    public Library() {
        this.librarians = new HashMap<>();
        this.students = new HashMap<>();
        this.books = new ArrayList<>();
        this.teacher = new HashMap<>();
        this.shelves = new HashMap<>();
        //this is the priority queue
    }

    // this are the getters with this getters we can now access all of this properties outside this class
    public Map<String, ShelvesByGenre> getShelves() {
        return shelves;
    }

    public Map<String, Librarian> getLibrarians() {
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


    public PriorityQueue<RequestObject> getRequestBook() {
        return requestBook;
    }

    //this will check whether the requested book is available
    //have not yet use this time stamp now i will use it later
    public void requestBook(String requesterEmail, String bookName, String bookGenre, String studentOrTeacher) {
        boolean genreExist = this.shelves.containsKey(bookGenre);
        boolean bookNameExist = false;
        boolean bookBorrowed = false;

        for (Book theBookName : this.shelves.get(bookGenre).getBooks()) {
            if (theBookName.getBookName().equals(bookName)) {
                bookNameExist = true;
                if (theBookName.getIsBorrowed()) {
                    bookBorrowed = true;
                } else {
                    System.out.println(bookName + " " + bookGenre + " has been requested successfully");
                }
                break;
            }
        }
        // i used all this one because of the if else statement
        if (!genreExist) {
            System.out.println("this book genre does not exist");
        }
        if (!bookNameExist) {
            System.out.println("this book does not exist");
        }
        if (bookBorrowed) {
            System.out.println("this book has been taken you cannot borrow it ");

        } else {

            System.out.println("this book have been added successfully ");
            addRequestBook(studentOrTeacher, requesterEmail, bookName, bookGenre);
        }
    }

    public void addRequestBook(String studentOrTeacher, String requesterEmail, String bookName, String bookGenre) {
        //enhanced switch
        arrivalCounter += 1;
        if (studentOrTeacher.equals("teacher")) {
            String teacherFullName = (this.teacher.get(requesterEmail).firstname + " " + this.teacher.get(requesterEmail).lastname);
            requestBook.offer(new RequestObject(bookName, bookGenre, teacherFullName, 1, arrivalCounter));
        } else if (studentOrTeacher.equals("student")) {
            String studentFullName = (this.students.get(requesterEmail).firstname + " " + this.students.get(requesterEmail).lastname);

            switch (this.students.get(requesterEmail).getLevel()) {
                case 400 ->
                        requestBook.offer(new RequestObject(bookName, bookGenre, studentFullName, 2, arrivalCounter));
                case 300 ->
                        requestBook.offer(new RequestObject(bookName, bookGenre, studentFullName, 3, arrivalCounter));
                case 200 ->
                        requestBook.offer(new RequestObject(bookName, bookGenre, studentFullName, 4, arrivalCounter));
                case 100 ->
                        requestBook.offer(new RequestObject(bookName, bookGenre, studentFullName, 5, arrivalCounter));
                default -> System.out.println("this does not exist");
            }
        } else {
            System.out.println("error please try again");
        }
    }


    //this is the method that we will use to serve the book

    public void serveBook(String bookName, String bookGenre) {
        //while this loop is running
        //it will run in the number of time the book is inside
        while (!this.requestBook.isEmpty()) {
            RequestObject theBooks = this.requestBook.poll();
            for (Book books : this.shelves.get(bookGenre).getBooks()) {
                //all the book will set their borrowed to true
                if (books.getBookName().equals(bookName)) {
                    books.setBorrowed(true);
                }
            }

        }
    }

    //this is the method for login students
    @Override
    public boolean loginAccount(String email, String password) {
        boolean userExist = this.students.containsKey(email);
        try {
            if (!userExist) {
                System.out.println("USER NOT FOUND !!!");
                return false;
            }
            //this either return true of false
            //this is directly to the student so its not to list that is why  i did it like this
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


    //this is not useful now i dont know maybe i should delete it now or not i am yet to decide
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

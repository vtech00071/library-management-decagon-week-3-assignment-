package org.example;

import org.example.model.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    // i will remove the library but i will think about what to do with it later
    static int libraryId = ((int) (Math.random() * 500000));
    static Scanner scanner = new Scanner(System.in);


    static void main() {
        Library library = new Library();
        //we have a map that takes string as key and shelves by genre as values
        //in this shelves we have list books
        ShelvesByGenre computerScienceBooks = new ShelvesByGenre("computer science", 1);
        ShelvesByGenre scienceFictionBooks = new ShelvesByGenre("science fictions", 1);

        //here we are going to create the books objects, and we are not using setters for is borrowed
        //because we can just change it anytime

        // Computer Science Books
        Book book1 = new Book("The Pragmatic Programmer", "Andrew Hunt", "computer science");
        Book book2 = new Book("Clean Code", "Robert C. Martin", "computer science");
        Book book3 = new Book("Structure and Interpretation of Computer Programs", "Harold Abelson", "computer science");
        // Science Fiction Books
        Book book4 = new Book("Dune", "Frank Herbert", "science fiction");
        Book book5 = new Book("Neuromancer", "William Gibson", "science fiction");
        Book book6 = new Book("The Left Hand of Darkness", "Ursula K. Le Guin", "science fiction");

        //here we are going to add the books to teh shelves
        //here we are going to add the computer science books first
        computerScienceBooks.getBooks().add(book1);
        computerScienceBooks.getBooks().add(book2);
        computerScienceBooks.getBooks().add(book3);
        //here we are going to add science fiction books
        scienceFictionBooks.getBooks().add(book4);
        scienceFictionBooks.getBooks().add(book5);
        scienceFictionBooks.getBooks().add(book6);

        //here we are going to be adding the shelves to the library
        //here we're adding the shelves but inside the shelves we now have multiple books in the shelves values as a list
        library.getShelves().put("computer science", computerScienceBooks);
        library.getShelves().put("science fiction", scienceFictionBooks);


        while (true) {
            System.out.println("==================================================");
            System.out.println("      WELCOME TO DECATRON LIBRARY SYSTEM");
            System.out.println("==================================================");
            System.out.println("A Smart Library Management Solution");
            System.out.println("==================================================");
            System.out.println("1. Continue");
            System.out.println("2. Exit");
            System.out.println("==================================================");
            System.out.print("Select Option: ");
            String option = scanner.nextLine().strip().toLowerCase();
            if (option.equals("1")) {
                mainMenu(library);
            } else {
                System.out.println("operation have been cancelled successfully ");
                break;
            }
        }
        scanner.close();
    }

    public static void mainMenu(Library library) {

        System.out.println("=================================================");
        System.out.println("       DECAGON  LIBRARY MANAGEMENT SYSTEM");
        System.out.println("=================================================");
        System.out.println("1. Create Account");
        System.out.println("2. Login");
        System.out.println("3. Exit");
        System.out.println("=================================================");
        System.out.print("Enter Option: ");
        String option = scanner.nextLine().strip().toLowerCase();

        switch (option) {
            case "1":
                createAccountMenu(library);
                break;
            case "2":
                loginMenu(library);
            default:
                System.out.println("thanks for coming you are free to visit another time");
        }
    }

    public static void loginMenu(Library library) {
        System.out.println("=================================================");
        System.out.println("               LOGIN                            |");
        System.out.println("=================================================");
        System.out.println("1. Student                                      |");
        System.out.println("2. Teacher                                      |");
        System.out.println("3. Back                                         |");
        System.out.println("=================================================");
        System.out.print("Enter Option: ");
        String option = scanner.nextLine().strip().toLowerCase();
        switch (option) {
            case "1":
                studentLogin(library);
                break;
            case "2":
                teacherLogin(library);
                break;
        }
    }

    //teacher and student should use the same login how do i go about it
    private static void teacherLogin(Library library) {
        System.out.print("EMAIL: ");
        String email = scanner.nextLine().strip().toLowerCase();
        System.out.print("PASSWORD: ");
        String password = scanner.nextLine().strip().toLowerCase();
        if (library.loginTeachersAccount(email, password)) {
            // with this we get to teacher map and i will use get to get it
            String teachersName = (library.getTeacher().get(email).getFirstname() + " " + library.getTeacher().get(email).getLastname());
            System.out.println(" WELCOME: " + library.getTeacher().get(email).getTitle() + " " + teachersName);

            System.out.println("=================================================");
            System.out.println("               DO YOU WANT TO BORROW A BOOK");
            System.out.println("=================================================");
            System.out.println("=================================================");
            System.out.println("               DO YOU WANT TO REQUEST FOR A BOOK");
            System.out.println("=================================================");
            System.out.println("1. borrow book: ");
            System.out.println("2. request for a book ");
            System.out.print("Enter Option: ");
            String option = scanner.nextLine().strip().toLowerCase();
            if (option.equals("1")) {
                System.out.print("BOOK-NAME: ");
                String bookName = scanner.nextLine();
                System.out.print("BOOK-GENRE: ");
                String bookGenre = scanner.nextLine();
                borrowBook(bookName, bookGenre, library);
            }
        }
    }

    //this is the login method
    public static void studentLogin(Library library) {
        System.out.print("EMAIL: ");
        String email = scanner.nextLine().strip().toLowerCase();
        System.out.print("PASSWORD: ");
        String password = scanner.nextLine().strip().toLowerCase();
        if (library.loginStudentAccount(email, password)) {
            String user = (library.getStudents().get(email).getFirstname() + " " + library.getStudents().get(email).getLastname());
            System.out.println(" WELCOME: " + library.getStudents().get(email).getTitle() + " " + user);

            System.out.println("=================================================");
            System.out.println("               DO YOU WANT TO BORROW A BOOK");
            System.out.println("=================================================");
            System.out.println("1. yes");
            System.out.println("2. exit");
            System.out.print("Enter Option: ");
            String option = scanner.nextLine().strip().toLowerCase();
            //this is the borrow method that i used before i will remove it immediate i am done with the
            //request and the first and first serve
            if (option.equals("1")) {
                System.out.print("BOOK-NAME: ");
                String bookName = scanner.nextLine();
                System.out.print("BOOK-GENRE: ");
                String bookGenre = scanner.nextLine();
                borrowBook(bookName, bookGenre, library);
            } else if (option.equals("2")) {

                String timestamps = LocalDateTime.now().toString();

                String requesterName = scanner.nextLine().strip().toLowerCase();
                System.out.print("BOOK-NAME: ");
                String bookName = scanner.nextLine();
                System.out.print("BOOK-GENRE: ");
                String bookGenre = scanner.nextLine();
                if (library.requestBook(bookName, bookGenre)) {
                    //so what this means is that it is book name that is going enter i didnt but if
                    //you check the stuffs you will see book
                    library.getRequestBook().offer(new RequestObject(requesterName, timestamps, bookName, bookGenre));
                    System.out.println(bookName + " has been requested successfully ");

                    //i am just testing this here i will still refactor the code
                    //but is still the librarian that will serve the book
                    //so this is not the final code

                    System.out.print("do you want to serve the book now yes/no? : ");
                    String question = scanner.nextLine().strip().toLowerCase();
                    if (question.equals("yes")) {
                      library.serveBook();
                    }
                }
            }
        }
    }

    public static void borrowBook(String bookName, String bookGenre, Library library) {
        if (library.borrowBook(bookName, bookGenre)) {
            System.out.println("this books has been borrowed successfull");
//
        } else {
            System.out.println("this book does not exist or it has been taken");
        }

    }

    public static void createAccountMenu(Library library) {

        System.out.println("=================================================");
        System.out.println("               CREATE ACCOUNT");
        System.out.println("=================================================");
        System.out.println("1. Student");
        System.out.println("2. Teacher");
        System.out.println("3. Back");
        System.out.println("=================================================");
        System.out.print("Enter Option: ");
        String option = scanner.nextLine().strip().toLowerCase();
        switch (option) {
            case "1":
                createAccountStudent(library);
                break;
            case "2":
                createAccountTeacher(library);
                break;
            default:
                System.out.println("thanks for coming you are free to visit another time");
                break;
        }
    }

    private static void createAccountTeacher(Library library) {
    }
    //this is the method that creates the students account


    //this method is for the student account creation and teacher account creation
    public static void createAccountStudent(Library library) {
        Map<String, String> userFields = new HashMap<>();
        // this will take in all the user fields or properties
        System.out.print("FIRSTNAME: ");
        String firstname = scanner.nextLine().strip().toLowerCase();
        System.out.print("LASTNAME: ");
        String lastname = scanner.nextLine().strip().toLowerCase();
        System.out.print("EMAIL: ");
        String email = scanner.nextLine().strip().toLowerCase();
        System.out.print("PASSWORD: ");
        String password = scanner.nextLine().strip().toLowerCase();
        System.out.print("CONFIRM-PASSWORD: ");
        String confirmPassword = scanner.nextLine().strip().toLowerCase();
        System.out.print("TITLE: ");
        String title = scanner.nextLine().strip().toLowerCase();
        System.out.print("choose T for teacher and S for student: ");
        String teacherOrStudent = scanner.nextLine().strip().toLowerCase();

        //then here we are going to load all those user fields into an hashmap

        userFields.put("firstname", firstname);
        userFields.put("lastname", lastname);
        userFields.put("email", email);
        userFields.put("password", password);
        userFields.put("confirmPassword", confirmPassword);
        userFields.put("title", title);

        if (library.createAccount(userFields)) {
            //i will still use setters for this because what if the constructors become longer
            //but for now i will use setters
            if (teacherOrStudent.equals("s")) {

                library.getStudents().put(email, new Students(firstname, lastname, email, password, title, libraryId));
                System.out.println("student have been added succesfully");
                System.out.println("LIBRARY-ID: " + libraryId);
                System.out.println("LIST OF STUDENTS IN THE LIBRARY");
                for (Students libraryStudentNames : library.getStudents().values()) {
                    //this will get the values which are the student objects, and it will begin to print it one after the other
                    //until it prints the last one
                    System.out.println(libraryStudentNames.getFirstname() + " " + libraryStudentNames.getLastname());
                    System.out.println(libraryStudentNames.getPassword());
                }
            } else if (teacherOrStudent.equals("t")) {
                library.getTeacher().put(email, new Teachers(firstname, lastname, email, password, title, libraryId));
                System.out.println("teachers have been added succesfully");
                System.out.println("LIBRARY-ID: " + libraryId);
                System.out.println("LIST OF TEACHERS IN THE LIBRARY");
                for (Teachers libraryTeacherNames : library.getTeacher().values()) {
                    //this will get the values which are the student objects, and it will begin to print it one after the other
                    //until it prints the last one
                    System.out.println(libraryTeacherNames.getFirstname() + " " + libraryTeacherNames.getLastname());
                    System.out.println(libraryTeacherNames.getPassword());
                }

            }
        }
    }
}


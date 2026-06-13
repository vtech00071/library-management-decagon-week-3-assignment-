package org.example;

import org.example.model.*;

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
        }
    }

    //this is the login method
    public static void studentLogin(Library library) {
        System.out.print("EMAIL: ");
        String email = scanner.nextLine().strip().toLowerCase();
        System.out.print("PASSWORD: ");
        String password = scanner.nextLine().strip().toLowerCase();
        if (library.loginStudentAccount(email, password)) {
            String studentName = (library.getStudents().get(email).getFirstname() + " " + library.getStudents().get(email).getLastname());
            System.out.println(" WELCOME: " + library.getStudents().get(email).getTitle() + " " + studentName);

            System.out.println("=================================================");
            System.out.println("               DO YOU WANT TO BORROW A BOOK");
            System.out.println("=================================================");
            System.out.println("1. yes");
            System.out.println("2. exit");
            System.out.print("Enter Option: ");
            String option = scanner.nextLine().strip().toLowerCase();
            if (option.equals("1")) {
                System.out.print("BOOK-NAME: ");
                String bookName = scanner.nextLine();
                System.out.print("BOOK-GENRE: ");
                String bookGenre = scanner.nextLine();
                borrowBook(bookName, bookGenre, library);
            } else {
                System.out.println("operation has been cancelled successfully");
            }
        }
    }

    public static void borrowBook(String bookName, String bookGenre, Library library) {
        if (library.borrowBook(bookName, bookGenre)) {
            System.out.println("this books has been borrowed successfull");
//
        }else{
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
    //this is the method that creates the students account

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
        }
    }

    public static void createAccountTeacher(Library library) {

    }
}

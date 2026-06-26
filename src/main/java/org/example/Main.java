package org.example;

import org.example.model.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    // i will remove the library but i will think about what to do with it later
    static Scanner scanner = new Scanner(System.in);
    static int arrivalCounter = 0;

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

        //here we are going to instantiate the librarian object
        Librarian librarian = new Librarian("adesola", "micheal", "adesolamicheal@gmai.com", "adexfem@gmail.com", "mr", 828282, "librarian");
        //we are going to add librarian to library
        library.getLibrarians().put(librarian.getEmail(), librarian);


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

    private static void mainMenu(Library library) {

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
            case "1" -> createAccount(library);
            case "2" -> loginMenu(library);
            default -> System.out.println("thanks for coming you are free to visit another time");
        }
    }

    //this method is for the student account creation and teacher account creation
    //this method is performing too much operation i will still simplify it
    private static void createAccount(Library library) {
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
        System.out.print("write student or teacher? : ");
        String identity = scanner.nextLine().strip().toLowerCase();
        System.out.print("level: ignore if you are not a student: ");
        String level = scanner.nextLine().strip().toLowerCase();
        //then here we are going to load all those user fields into an hashmap

        userFields.put("firstname", firstname);
        userFields.put("lastname", lastname);
        userFields.put("email", email);
        userFields.put("password", password);
        userFields.put("confirmPassword", confirmPassword);
        userFields.put("title", title);
        int libraryId = ((int) (Math.random() * 500000));
        switch (library.createAccount(userFields)){
            case EMPTY_FIELDS -> System.out.println("some fields are empty please try again");
            case INVALID_EMAIL -> System.out.println("invalid email please try again");
            case PASSWORDS_DOES_NOT_MATCH -> System.out.println("password and confirm password not match");
            case INVALID_TITLE -> System.out.println("invalid title e.g mr,mrs....");
            case ACCOUNT_CREATION_SUCCESSFUL -> {
                System.out.println("account creation successful");
                if (identity.equals("student")) {
                   if (!level.isBlank()){
                       Person student = new Students(firstname, lastname, email, password, title, libraryId, identity);
                       student.setLevel(level);
                       library.getLibraryUsers().put(email, student);
                       System.out.println("User have been added!");
                   }else {
                       System.out.println("you are a student your level section cannot be blank");
                   }
                } else {
                    library.getLibraryUsers().put(email, new Teachers(firstname, lastname, email, password, title, libraryId, identity));
                    System.out.println("User have been added!");
                }
                System.out.println("LIST OF ALL THE LIBRARY USERS ");
                //this loop will generate all the names of the library users
                for (Person libraryUsers : library.getLibraryUsers().values()) {
                    //i want the level of the student to display
                    String userNames = (libraryUsers.getFirstname() + " " + libraryUsers.getLastname());
                    System.out.println("NAME: " + userNames + " IDENTITY:  " + libraryUsers.getIdentity());
                }
            }
        }
    }



    private static void loginMenu(Library library) {
        System.out.println("=================================================");
        System.out.println("                  LOGIN                          ");
        System.out.println("=================================================");
        System.out.println("1. User");
        System.out.println("2. Librarian");
        System.out.println("=================================================");
        System.out.print("Enter Option: ");
        String option = scanner.nextLine().strip().toLowerCase();
        switch (option) {
            case "1" -> Login(library);
            case "2" -> librarianLogin(library);
            default -> System.out.println("invalid option");
        }
    }

    //this method is where the librarian will login and serve the book
    private static void librarianLogin(Library library) {
        System.out.print("EMAIL: ");
        String email = scanner.nextLine().strip().toLowerCase();
        System.out.print("PASSWORD: ");
        String password = scanner.nextLine().strip().toLowerCase();
        switch (library.loginLibrarian(email, password)) {
            case USER_DOES_NOT_EXIST -> System.out.println("user does not exist or invalid email");
            case INVALID_PASSWORD -> System.out.println("invalid password please try again");
            case LOGIN_SUCCESSFUL -> librarianServeBookDashboard(library, email);
        }
    }

    //this method will prompt the librarian to serve the book
    private static void librarianServeBookDashboard(Library library, String email) {
        String user = (library.getLibrarians().get(email).getFirstname() + " " + library.getLibrarians().get(email).getLastname());
        System.out.println(" WELCOME: " + library.getLibrarians().get(email).getTitle() + " " + user);
        System.out.print("do you want to serve the book now yes/no? : ");
        String question = scanner.nextLine().strip().toLowerCase();
        if (question.equals("yes")) {
            library.serveBook();
        } else {
            System.out.println("operation has been cancelled");
        }
    }

    //teacher and student should use the same login how do i go about it
    private static void Login(Library library) {
        System.out.print("EMAIL: ");
        String email = scanner.nextLine().strip().toLowerCase();
        System.out.print("PASSWORD: ");
        String password = scanner.nextLine().strip().toLowerCase();
        switch (library.loginAccount(email, password)) {
            case USER_DOES_NOT_EXIST -> System.out.println("user does not exist or invalid email");
            case INVALID_PASSWORD -> System.out.println("invalid password please try again");
            case LOGIN_SUCCESSFUL -> requestBookDashboard(library, email);
        }
    }

    //this dashboard is `to request for a book
    private static void requestBookDashboard(Library library, String email) {
        String user = (library.getLibraryUsers().get(email).getFirstname() + " " + library.getLibraryUsers().get(email).getLastname());
        System.out.println(" WELCOME: " + library.getLibraryUsers().get(email).getTitle() + " " + user);

        System.out.println("do you want to request for a book? yes/no: ");
        System.out.print("Enter Option: ");
        String option = scanner.nextLine().strip().toLowerCase();
        if (option.equals("yes")) {
            bookRequestMenu(library);
        } else {
            System.out.println("thanks for visiting come next time");
        }
    }

    private static void bookRequestMenu(Library library) {
        System.out.print("EMAIL: ");
        String requesterEmail = scanner.nextLine().strip().toLowerCase();
        System.out.print("BOOK-NAME: ");
        String bookName = scanner.nextLine();
        System.out.print("BOOK-GENRE: ");
        String bookGenre = scanner.nextLine();
        //i am also going to create a file and add all the history of borrowed book
        switch (library.requestBook(bookName, bookGenre)) {
            case GENRE_NOT_FOUND -> System.out.println("this genre that you have looking for does not exist");
            case BOOK_HAS_BEEN_BORROWED -> System.out.println("this book has been borrowed");
            case BOOK_DOES_NOT_EXIST -> System.out.println("this book does not exist");
            case BOOK_REQUESTED_SUCCESSFULLY -> {
                System.out.println("this book has been requested successfully ");
                //this method will add the books inside the queue
                addRequestBook(requesterEmail, bookName, bookGenre, library);
            }
        }
    }

    private static void addRequestBook(String requesterEmail, String bookName, String bookGenre, Library library) {
        //enhanced switch
        String usersName = (library.getLibraryUsers().get(requesterEmail).getFirstname() + " " + library.getLibraryUsers().get(requesterEmail).getFirstname());
        arrivalCounter += 1;
        if (library.getLibraryUsers().get(requesterEmail).getIdentity().equals("teacher")) {

            library.getRequestBook().offer(new RequestObject(bookName, bookGenre, usersName, 1, arrivalCounter));
        } else if (library.getLibraryUsers().get(requesterEmail).getIdentity().equals("student")) {

            switch (library.getLibraryUsers().get(requesterEmail).getLevel()) {
                case "400" -> library.getRequestBook().offer(new RequestObject(bookName, bookGenre, usersName, 2, arrivalCounter));
                case "300" -> library.getRequestBook().offer(new RequestObject(bookName, bookGenre, usersName, 3, arrivalCounter));
                case "200" -> library.getRequestBook().offer(new RequestObject(bookName, bookGenre, usersName, 4, arrivalCounter));
                case "100" -> library.getRequestBook().offer(new RequestObject(bookName, bookGenre, usersName, 5, arrivalCounter));
                default -> System.out.println("this does not exist");
            }
        } else {
            System.out.println("error please try again");
        }
    }
}


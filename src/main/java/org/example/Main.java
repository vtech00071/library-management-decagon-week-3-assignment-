package org.example;

import org.example.model.*;

import javax.xml.crypto.dsig.spec.XSLTTransformParameterSpec;
import java.util.HashMap;
import java.util.Map;

import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);


    static void main() {
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
                mainMenu();
            } else {
                System.out.println("operation have been cancelled successfully ");
                break;
            }
        }
        scanner.close();
    }

    public static void mainMenu() {
        Person teacher = new Teachers();
        Person student = new Students();
        Person librarian = new Librarian();
        Library library = new Library();


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
                createAccountMenu(teacher, student, library);
                break;
            case "2":
                loginMenu(teacher, student, library);
            default:
                System.out.println("thanks for coming you are free to visit another time");
        }

    }

    public static void loginMenu(Person teacher, Person student, Library library) {
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
                studentLogin(student, library);
        }
    }

    public static void studentLogin(Person student, Library library) {
        System.out.print("LIBRARY-ID: ");
        int  libraryId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("PASSWORD: ");
        String password = scanner.nextLine().strip().toLowerCase();
        if (library.loginStudentAccount(libraryId, password)) {
            String fullname = (library.getStudents().get(libraryId).getFirstname() + " " + library.getStudents().get(libraryId).getLastname());
            System.out.println("welcome: " + fullname);

        }
    }

    public static void createAccountMenu(Person teacher, Person student, Library library) {
        Map<String, String> userFields = new HashMap<>();

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
                createAccountStudent(student, userFields, library);
                break;
            case "2":
                createAccountTeacher(teacher, userFields, library);
                break;
            default:
                System.out.println("thanks for coming you are free to visit another time");
                break;
        }

    }
    //this is the method that creates the students account

    public static void createAccountStudent(Person student, Map<String, String> userFields, Library library) {
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

        if (student.createAccount(userFields)) {
            //for every loop this will create a new random number;
            //i am still thinking of putting this up but i think this is the best place
            int libraryId = ((int) (Math.random() * 500000));

            //so here we are adding the properties now to the object through the setters
            student.setFirstname(firstname);
            student.setLastname(lastname);
            student.setEmail(email);
            student.setPassword(password);
            student.setTitle(title);
            student.setLibraryId(libraryId);

            //now we want to put the student inside the library
            library.getStudents().put(libraryId, student);
            System.out.println("student have been added succesfully");
            System.out.println("LIBRARY-ID: " + libraryId);
            System.out.println("LIST OF STUDENTS IN THE LIBRARY");
            for (Person libraryStudentNames : library.getStudents().values()) {
                //this will get the values which are the student objects and it will begin to print it one after the other
                //until it prints the last one
                System.out.println(libraryStudentNames.getFirstname() + " " + libraryStudentNames.getLastname());
                System.out.println(libraryStudentNames.getPassword());
            }
        }
    }

    public static void createAccountTeacher(Person teacher, Map<String, String> userFields, Library library) {
    }


}

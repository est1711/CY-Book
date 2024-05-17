package CYBook;

import java.net.ConnectException;
import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MainApp {

    private static ArrayList<User> usersList;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice = 0;

        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3306/login_schema",
                    "root",
                    "book"
            );


            //add loading database methode

            while (true) {
                System.out.println("Choose the option of your choice by typing the associated number");
                System.out.println("(1) --> Book search ");
                System.out.println("(2) --> Create a customer account");
                System.out.println("(3) --> Display customer information");
                System.out.println("(4) --> View all overdue books");
                System.out.println("\n(0) --> Quit");
                System.out.print("\nYour choice : ");

                try {
                    choice = sc.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("\nInput is incorrect\n");
                    sc.nextLine(); // Clear the input buffer
                    continue;
                }

                switch (choice) {
                    case 0:
                        System.out.println("Exiting the application. Goodbye!");
                        connection.close();
                        System.exit(0);
                    case 1:
                        bookSearch();
                        break;
                    case 2:
                        createCustomerAccount(sc, connection);
                        break;
                    case 3:
                        displayCustomerInformation(sc,connection);
                        break;
                    case 4:
                        viewOverdueBooks();
                        break;
                    default:
                        System.out.println("Invalid choice. Please select a number between 0 and 4.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static String getUserInput(Scanner sc, String prompt) {
        System.out.print(prompt);
        return sc.next().trim();
    }

    private static boolean isValidEmail(String email) {
        // Expression régulière pour valider le format de l'e-mail
        String emailRegex = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";
        return email.matches(emailRegex);
    }

    private static void createCustomerAccount(Scanner sc, Connection connection) {
        String name_customer;
        String last_name_customer;
        String email_customer;

        name_customer = getUserInput(sc, "Enter name: ");
        if (name_customer.isEmpty()) {
            System.out.println("Name cannot be empty.");
            return;
        }

        last_name_customer = getUserInput(sc, "Enter last name: ");
        if (last_name_customer.isEmpty()) {
            System.out.println("Last name cannot be empty.");
            return;
        }

        email_customer = getUserInput(sc, "Enter email: ");
        if (email_customer.isEmpty() || !isValidEmail(email_customer)) {
            System.out.println("Invalid email format.");
            return;
        }

        if (!(User.isInDataBase(name_customer, last_name_customer, connection))) {
            User u1 = new User(name_customer, last_name_customer, email_customer, connection);
            System.out.println("The customer informations are : \n-->" + u1.getFirstName() + "\n-->" + u1.getLastName() + "\n-->" + u1.getEmail());
        } else {
            System.out.println("Customer already in the data base");
        }
    }

    private static void bookSearch() {
        System.out.println("Book search functionality will be implemented here.");
    }

    private static void displayCustomerInformation(Scanner sc, Connection connection) {
        String name_customer;
        String last_name_customer;

        System.out.print("Enter name: ");

        try {
            name_customer = sc.next();
        } catch (InputMismatchException e) {
            System.out.println("\nInput is incorrect\n");
            sc.nextLine(); // Clear the input buffer
            return;
        }

        System.out.print("Enter last name: ");

        try {
            last_name_customer = sc.next();
        } catch (InputMismatchException e) {
            System.out.println("\nInput is incorrect\n");
            sc.nextLine(); // Clear the input buffer
            return;
        }

        int id_customer = User.userConnection(name_customer,last_name_customer,connection);

        if (id_customer>-1){
            customerChoice(id_customer,sc,connection);
        }
        else{
            System.out.println("The user doesn't exist");
        }


    }

    private static void viewOverdueBooks() {
        System.out.println("View overdue books functionality will be implemented here.");
    }

    private static void customerChoice(int id_customer,Scanner sc,Connection connection){
            while(true){
                int choice = 1;


                System.out.println("Choose the option of your choice by typing the associated number");
                System.out.println("(1) --> Add borrowing");
                System.out.println("(2) --> Delete borrowing");
                System.out.println("(3) --> Delete account ");
                System.out.println("(4) --> Change account informations");
                System.out.println("\n(0) --> Quit");

                System.out.print("\nYour choice : ");

                try {
                    choice = sc.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("\nInput is incorrect\n");
                    sc.nextLine();
                    continue;
                }
                if (choice==0)
                    break;
                if(choice==4){
                    changeAccountInformations(id_customer,sc,connection);

                }

            }

        }

    private static void changeAccountInformations(int id_customer, Scanner sc, Connection connection) {
        while (true) {
            int choice = 1;
            System.out.println("Choose the option of your choice by typing the associated number");
            System.out.println("(1) --> Change your name");
            System.out.println("(2) --> Change your last name");
            System.out.println("(3) --> Change your e-mail address");
            System.out.println("\n(0) --> Quit");
            System.out.print("\nYour choice : ");
            try {
                choice = sc.nextInt();
                if (choice < 0 || choice > 3) {
                    System.out.println("Invalid choice. Please choose again.");
                    continue;
                }
            } catch (InputMismatchException e) {
                System.out.println("Input is incorrect. Please enter a number.");
                sc.nextLine();
                continue;
            }
            switch (choice) {
                case 0:
                    return;
                case 1:
                    updateFirstName(id_customer, sc, connection);
                    break;
                case 2:
                    updateLastName(id_customer, sc, connection);
                    break;
                case 3:
                    updateEmail(id_customer, sc, connection);
                    break;
            }
        }
    }

    private static void updateFirstName(int id_customer, Scanner sc, Connection connection) {
        String newFirstName;
        System.out.print("Enter your new first name: ");
        newFirstName = sc.next().trim();
        if (newFirstName.isEmpty()) {
            System.out.println("First name cannot be empty. Operation canceled.");
            return;
        }
        boolean firstNameUpdated = User.updateFirstNameById(id_customer, newFirstName, connection);
        if (firstNameUpdated) {
            System.out.println("First name updated successfully.");
        } else {
            System.out.println("Failed to update first name.");
        }
    }

    private static void updateLastName(int id_customer, Scanner sc, Connection connection) {
        String newLastName;
        System.out.print("Enter your new last name: ");
        newLastName = sc.next().trim();
        if (newLastName.isEmpty()) {
            System.out.println("Last name cannot be empty. Operation canceled.");
            return;
        }
        boolean lastNameUpdated = User.updateLastNameById(id_customer, newLastName, connection);
        if (lastNameUpdated) {
            System.out.println("Last name updated successfully.");
        } else {
            System.out.println("Failed to update last name.");
        }
    }

    private static void updateEmail(int id_customer, Scanner sc, Connection connection) {
        String newEmail;
        System.out.print("Enter your new email address: ");
        newEmail = sc.next().trim();
        if (newEmail.isEmpty() || !newEmail.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
            System.out.println("Invalid email format. Operation canceled.");
            return;
        }
        boolean emailUpdated = User.updateEmailById(id_customer, newEmail, connection);
        if (emailUpdated) {
            System.out.println("Email address updated successfully.");
        } else {
            System.out.println("Failed to update email address.");
        }
    }


}



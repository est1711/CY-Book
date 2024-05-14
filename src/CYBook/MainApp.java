package CYBook;

import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MainApp {



    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice = 0;

        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3306/login_schema",
                    "root",
                    "book"
            );


            //add loading data base methode


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
                        displayCustomerInformation();
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

    private static void createCustomerAccount(Scanner sc, Connection connection) {
        System.out.print("Enter name: ");
        String name_customer;
        String last_name_customer;
        String email_customer;

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

        System.out.print("Enter email: ");

        try {
            email_customer = sc.next();
        } catch (InputMismatchException e) {
            System.out.println("\nInput is incorrect\n");
            sc.nextLine(); // Clear the input buffer
            return;
        }


        if(!(User.isInDataBase(name_customer,last_name_customer,connection))) {
            User u1 = new User(name_customer, last_name_customer, email_customer, connection);
            System.out.println("The customer informations are : \n-->" + u1.getFirstName() + "\n-->" + u1.getLastName() + "\n-->" + u1.getEmail());
        }
        else {
            System.out.println("Customer already in the data base");
        }


    }

    public static void bookSearch() {
        System.out.println("Book search functionality will be implemented here.");
    }

    public static void displayCustomerInformation() {
        System.out.println("Display customer information functionality will be implemented here.");
    }

    public static void viewOverdueBooks() {
        System.out.println("View overdue books functionality will be implemented here.");
    }
}

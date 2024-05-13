package CYBook;
import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


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

                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM users");

                while (resultSet.next()) {
                    System.out.println(resultSet.getString("user_name"));
                    System.out.println(resultSet.getString("user_password"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            while(true){
                System.out.println("Choose the option of your choice by typing the associated number");
                System.out.println("(1) --> Book search ");
                System.out.println("(2) --> Create a customer account");
                System.out.println("(3) --> Display customer information");
                System.out.println("(4) --> View all overdue books");
                System.out.println("\n(0) --> Quit");
                System.out.print("\nYour choice : ");


                try {
                    choice = sc.nextInt();
                }
                catch (InputMismatchException e){
                    System.out.println("\nInput is incorrect\n");
                    sc.nextLine(); // Clear the input buffer
                    continue;
                }

                switch (choice) {
                    case 0:
                        System.out.println("Exiting the application. Goodbye!");
                        System.exit(0);
                    case 1:
                        bookSearch();
                        break;
                    case 2:
                        createCustomerAccount();
                        break;
                    case 3:
                        displayCustomerInformation();
                        break;
                    case 4:
                        viewOverdueBooks();
                        break;
                    default:
                        System.out.println("Invalid choice. Please select a number between 1 and 5.");
                }
            }




        }

        public static void bookSearch() {
            System.out.println("Book search functionality will be implemented here.");
        }

        public static void createCustomerAccount() {
            System.out.println("Create customer account functionality will be implemented here.");
        }

        public static void displayCustomerInformation() {
            System.out.println("Display customer information functionality will be implemented here.");
        }

        public static void viewOverdueBooks() {
            System.out.println("View overdue books functionality will be implemented here.");
        }
    }



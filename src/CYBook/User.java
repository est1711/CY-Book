package CYBook;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class User {
    private String firstName;
    private String lastName;
    private String email;
    public User(String firstName, String lastName, String email, Connection connection) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        addToDataBase(firstName, lastName, email, connection);

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    private static boolean addToDataBase(String name_customer, String last_name_customer, String email_customer, Connection connexion) {
        boolean userAdded = false;
        try {
            if (isInDataBase(name_customer, last_name_customer, connexion)) {
                System.out.println("This customer is already in the data base");
                return false;
            }

            String query = "INSERT INTO customer (name_customer, last_name_customer, email_customer) VALUES (?, ?, ?)";
            try (PreparedStatement preparedStatement = connexion.prepareStatement(query)) {
                preparedStatement.setString(1, name_customer);
                preparedStatement.setString(2, last_name_customer);
                preparedStatement.setString(3, email_customer);

                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    userAdded = true;
                    System.out.println("Customer add with success");
                } else {
                    System.out.println("The user can't be added");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userAdded;
    }

    public static boolean isInDataBase(String name_customer, String lastName_customer, Connection connection) {
        String query = "SELECT id_customer FROM customer WHERE name_customer = ? AND last_name_customer = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, name_customer);
            preparedStatement.setString(2, lastName_customer);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}

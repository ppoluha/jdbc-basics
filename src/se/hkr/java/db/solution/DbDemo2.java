package se.hkr.java.db.solution;

import java.sql.*;

public class DbDemo2 {
    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test",
                "root",
                "mauFJcuf5dhRMQrjj");
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM customer WHERE last_name = ? AND first_name = ?")) {

            // Set the parameter values for the prepared statement
            preparedStatement.setString(1, "Doe"); // First conditional: LastName
            preparedStatement.setString(2, "John"); // Second conditional: FirstName

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int customerId = resultSet.getInt("id");
                    String lastName = resultSet.getString("last_name");
                    String firstName = resultSet.getString("first_name");

                    System.out.printf("CustomerID: %d, LastName: %s, FirstName: %s%n",
                            customerId, lastName, firstName);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

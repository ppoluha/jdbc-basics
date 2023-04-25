package se.hkr.java.db.solution;

import java.sql.*;

public class DbDemo1 {
    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test",
                "root",
                "mauFJcuf5dhRMQrjj");
             ResultSet resultSet = connection
                     .createStatement()
                     .executeQuery("SELECT * FROM customer")) {

            while (resultSet.next()) {
                int customerId = resultSet.getInt("id");
                String lastName = resultSet.getString("last_name");
                String firstName = resultSet.getString("first_name");
                System.out.printf("CustomerID: %d, LastName: %s, FirstName: %s%n",
                        customerId, lastName, firstName);
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

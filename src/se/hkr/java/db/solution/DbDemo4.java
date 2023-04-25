package se.hkr.java.db.solution;

import java.sql.*;

public class DbDemo4 {
    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test",
                "root",
                "mauFJcuf5dhRMQrjj")) {

            String insertQuery = "INSERT INTO customer (first_name, last_name) VALUES (?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                preparedStatement.setString(1, "John");
                preparedStatement.setString(2, "Doe");

                int rowsAffected = preparedStatement.executeUpdate();
                System.out.println(rowsAffected + " row(s) inserted successfully.");
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

package se.hkr.java.db.solution;

import java.sql.*;

public class DbDemo3 {
    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test",
                "root",
                "mauFJcuf5dhRMQrjj")) {

            String updateQuery = "UPDATE customer SET last_name = ?, first_name = ? WHERE id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
                preparedStatement.setString(1, "Doe");
                preparedStatement.setString(2, "Ben");
                preparedStatement.setInt(3, 1);

                int rowsAffected = preparedStatement.executeUpdate();
                System.out.println(rowsAffected + " row(s) updated successfully.");
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

package se.hkr.java.db.solution;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

record Employee (int id, String name, int age) {}

class EmployeeDAO {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/test";
    private static final String USER = "root";
    private static final String PASS = "mauFJcuf5dhRMQrjj";

    public void addEmployee(Employee employee) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO employee (name, age) VALUES (?, ?)")) {
            stmt.setString(1, employee.name());
            stmt.setInt(2, employee.age());
            stmt.executeUpdate();
            System.out.println("Employee added successfully!");
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    public List<Employee> getEmployees() {
        List<Employee> employees = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM employee");
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                Employee employee = new Employee(id, name, age);
                employees.add(employee);
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
        return employees;
    }
}

public class EmployeeManager {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EmployeeDAO employeeDAO = new EmployeeDAO();

        // Display menu
        boolean exit = false;
        while (!exit) {
            System.out.println("Employee Management System");
            System.out.println("1. Add Employee");
            System.out.println("2. View Employees");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    // Add Employee
                    System.out.print("Enter employee name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter employee age: ");
                    int age = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character

                    Employee employee = new Employee(0, name, age);
                    employeeDAO.addEmployee(employee);
                    break;

                case 2:
                    // View Employees
                    List<Employee> employees = employeeDAO.getEmployees();
                    System.out.println("Employee List:");
                    for (Employee emp : employees) {
                        System.out.println(emp);
                    }
                    break;

                case 3:
                    // Exit
                    exit = true;
                    break;

                default:
                    System.out.println("Invalid choice. Please enter a valid choice.");
                    break;
            }
        }
        scanner.close();
        System.out.println("Thank you for using Employee Management System. Goodbye!");
    }
}

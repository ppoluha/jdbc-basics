package se.hkr.java.db.playground;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

record Employee (int id, String name, int age) {}

class EmployeeDAO {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/test";
    private static final String USER = "root";
    private static final String PASS = "mauFJcuf5dhRMQrjj";

    public void addEmployee(Employee employee) {
    }

    public List<Employee> getEmployees() {
        return new ArrayList<>();
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

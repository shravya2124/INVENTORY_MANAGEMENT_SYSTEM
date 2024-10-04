package Inventory_System;
import java.sql.*;
import java.util.Scanner;

public class InventoryManagementSystem {

    private static final String URL = "jdbc:mysql://localhost:3306/inventory";
    private static final String USER = "root";
    private static final String PASSWORD ="root123";

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("Inventory Management System");
                System.out.println("\n1. Add Product");
                System.out.println("2. Update Product");
                System.out.println("3. Delete Product");
                System.out.println("4. Exit");
                System.out.print("\n Enter your choice: ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        addProduct();
                        break;
                    case 2:
                        System.out.print("Enter product ID to update: ");
                        int idToUpdate = scanner.nextInt();
                        updateProduct(idToUpdate);
                        break;
                    
                    case 3:
                        deleteProduct();
                        break;
                    case 4:
                        System.out.println("Exiting...");
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    private static void addProduct() {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO products (name, quantity, price) VALUES (?, ?, ?)")) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter product name: ");
            String name = scanner.nextLine();
            System.out.print("Enter product quantity: ");
            int quantity = scanner.nextInt();
            System.out.print("Enter product price: ");
            double price = scanner.nextDouble();

            statement.setString(1, name);
            statement.setInt(2, quantity);
            statement.setDouble(3, price);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Product added successfully.");
            } else {
                System.out.println("Failed to add product.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void updateProduct(int id) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("UPDATE products SET name=?, quantity=?, price=? WHERE id=?")) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter new product name: ");
            String name = scanner.next();
            System.out.print("Enter new product quantity: ");
            int quantity = scanner.nextInt();
            System.out.print("Enter new product price: ");
            double price = scanner.nextDouble();

            statement.setString(1, name);
            statement.setInt(2, quantity);
            statement.setDouble(3, price);
            statement.setInt(4, id);

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Product updated successfully.");
            } else {
                System.out.println("No product found with the given ID.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private static void deleteProduct() {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM products WHERE id=?")) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter product ID: ");
            int id = scanner.nextInt();

            statement.setInt(1, id);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Product deleted successfully.");
            } else {
                System.out.println("No product found with the given ID.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private static void exit() {
        System.out.println("Exiting the application...");
        System.exit(0); // Exit the application with status 0
    }
}

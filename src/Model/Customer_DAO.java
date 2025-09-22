/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import Controller.DataBase_Connection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class Customer_DAO {
    private final Connection conn;

    public Customer_DAO() {
        // Initialize database connection
        this.conn = DataBase_Connection.dbConnection();
    }

    // Add a new customer
    public boolean addCustomer(Customer customer) {
        String sql = "INSERT INTO customer (idCus, Cus_name, cNum) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, customer.getCustomerID());
            pstmt.setString(2, customer.getName());
            pstmt.setInt(3, customer.getContactDetails());
            pstmt.executeUpdate();
            return true;
        }catch (SQLException e) {
            System.out.println("Error adding customer: " + e.getMessage());
            return false;
        }
    }
    // Update an existing customer
    public boolean updateCustomer(Customer customer) throws SQLException {
        String sql = "UPDATE customer SET Cus_name = ?, cNum = ? WHERE idCus = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, customer.getName());
            pstmt.setInt(2, customer.getContactDetails());
            pstmt.setInt(3, customer.getCustomerID());
            pstmt.executeUpdate();
            return true;
        }
    }
    // Delete a customer
    public void deleteCustomer(int customerID) throws SQLException {
        String sql = "DELETE FROM customer WHERE idCus = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, customerID);
            pstmt.executeUpdate();
        }
    }
    // Get a customer by ID
    public Customer getCustomerById(int customerID) throws SQLException {
        String sql = "SELECT * FROM customer WHERE idCus = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, customerID);
            ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            return new Customer(
                rs.getInt("idCus"),
                rs.getString("Cus_name"),
                rs.getInt("cNum")
            );
        } else {
            return null;
        }
        } catch (SQLException e) {
            throw new SQLException("Error fetching customer: " + e.getMessage());
        }
    }

}

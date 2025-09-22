/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Inventory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class InventoryController {
    
    private final Connection conn;

    public InventoryController() {
        this.conn = DataBase_Connection.dbConnection();
    }

    public Inventory getInventoryByCakeId(int cakeId) {
        Inventory inventory = null; // Initialize inventory
        String query = "SELECT idCake, Name, Quantity FROM product WHERE idCake = ? ";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, cakeId); // Set the cakeId parameter
            ResultSet rs = pstmt.executeQuery(); // Execute the query

            if (rs.next()) {
                return new Inventory(rs.getInt("idCake"), 
                                 rs.getString("Name"), 
                                 rs.getInt("Quantity")
                ); 
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving inventory: " + e.getMessage());
        }
        return null; // Return the inventory
    }
  
    public boolean updateInventoryStock(int cakeId, int quantity, String name) {
        String query = "UPDATE product SET Name = ?, Quantity = ? WHERE idCake = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, name);   // Set the name parameter
            pstmt.setInt(2, quantity);   // Set the new quantity
            pstmt.setInt(3, cakeId);     // Set the cake ID to update
        
            int rowsAffected = pstmt.executeUpdate(); // Execute the update
            return rowsAffected > 0; // Return true if update was successful
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Return false in case of an error
        }
    }

  
    public boolean addInventoryItem(int cakeId, int initialStock) {
        String query = "INSERT INTO product (idCake, Quantity) VALUES (?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, cakeId);
            pstmt.setInt(2, initialStock);
            int rowsAffected = pstmt.executeUpdate();

            return rowsAffected > 0; // Return true if insertion was successful
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Return false in case of an error
        }
    }

    public List<Inventory> getProducts() {
        List<Inventory> productList = new ArrayList<>();
        String query = "SELECT idCake, Name, Quantity FROM product";

        try (PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            // Loop through the result set and add each product to the list
            while (rs.next()) {
                Inventory product = new Inventory(
                        rs.getInt("idCake"),
                        rs.getString("Name"),
                        rs.getInt("Quantity")
                );
                productList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList; // Return the list of products
    }
    // Method to check quantities and show alert
    public List<String> checkLowStock(List<Inventory> productList) {
        List<String> lowStockProducts = new ArrayList<>();

        for (Inventory product : productList) {
            int quantity = product.getStock();
            if (quantity <= 2) {  // Check if quantity is 0 or less than 2
                lowStockProducts.add(product.getName());
            }
        }

        return lowStockProducts;  // Return list of low stock product names
    }


}



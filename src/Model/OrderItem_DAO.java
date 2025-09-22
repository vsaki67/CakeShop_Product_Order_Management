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

import Model.OrderItem;

import java.util.ArrayList;
import java.util.List;

public class OrderItem_DAO {
    private final Connection conn;

    public OrderItem_DAO() {
        // Initialize database connection
        this.conn = DataBase_Connection.dbConnection();
    }

    // Add a new order item
    public boolean addOrderItem(OrderItem orderItem) throws SQLException {
        String sql = "INSERT INTO orderitem (idOrder_item, qauntity, price, orderID, idCake) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, orderItem.getOrderItem());
            pstmt.setInt(2, orderItem.getQuantity());
            pstmt.setFloat(3, orderItem.getPrice());
            pstmt.setInt(4, orderItem.getOrderID());
            pstmt.setInt(5, orderItem.getIdCake());
            pstmt.executeUpdate();
            return true;
        }
    }

    // Update an order item
    public boolean updateOrderItem(OrderItem orderItem) throws SQLException {
        String sql = "UPDATE orderitem SET qauntity = ?, price = ?, orderID = ?, idCake = ? WHERE idOrder_item = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, orderItem.getQuantity());
            pstmt.setFloat(2, orderItem.getPrice());
             pstmt.setInt(3, orderItem.getOrderID());
            pstmt.setInt(4, orderItem.getIdCake()); // Updated to idCake
            pstmt.setInt(5, orderItem.getOrderItem()); // ID of the order item to be updated
            pstmt.executeUpdate();
            return true;
        }
    }

    // Delete order items by orderID
    public void deleteOrderItemsByOrderID(int orderID) throws SQLException {
        String sql = "DELETE FROM orderitem WHERE orderID = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, orderID);
            pstmt.executeUpdate();
        }
    }

    public List<OrderItem> getOrderItemsByOrderID(int orderID) throws SQLException {
        String sql = "SELECT * FROM orderitem WHERE orderID = ?";
        List<OrderItem> items = new ArrayList<>();
    
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, orderID);
            ResultSet rs = pstmt.executeQuery();
        
        // Loop through the result set and map to OrderItem objects
            while (rs.next()) {
                OrderItem item = new OrderItem(
                    rs.getInt("idOrder_item"),   // Corrected column name from your table
                    rs.getInt("qauntity"),      // Corrected typo "qauntity" to "quantity"
                    rs.getFloat("price"),
                    rs.getInt("orderID"),
                    rs.getInt("idCake")
                );
                items.add(item);
            }
        }
        return items;
    }

}

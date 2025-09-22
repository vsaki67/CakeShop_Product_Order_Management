/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author asusx
 */
import Model.PlaceOrder;

import Controller.DataBase_Connection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class PlaceOrder_DAO {
    private final Connection conn;

    public PlaceOrder_DAO() {
        // Initialize database connection
        this.conn = DataBase_Connection.dbConnection();
    }

    // Add a new place order record
    public boolean addPlaceOrder(PlaceOrder placeOrder) throws SQLException {
        String sql = "INSERT INTO placeorder (orderDate, orderID, idCus ) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDate(1, java.sql.Date.valueOf(placeOrder.getOrderDate()));
            pstmt.setInt(2, placeOrder.getOrderID());
            pstmt.setInt(3, placeOrder.getCustomerID());
            pstmt.executeUpdate();
            return true;
        }
    }
    
    // Update a place order
    public boolean updatePlaceOrder(LocalDate orderDate, int customerID, int orderID) throws SQLException {
    // Ensure you're updating the correct table (e.g., placeorder instead of orderitem)
        String sql = "UPDATE placeorder SET orderDate = ?, idCus = ? WHERE orderID = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
        // Convert LocalDate to SQL Date
            pstmt.setDate(1, java.sql.Date.valueOf(orderDate));
            pstmt.setInt(2, customerID);
            pstmt.setInt(3, orderID);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error updating place order: " + e.getMessage());
        }
    }


    // Delete a place order by orderID
    public void deletePlaceOrder(int orderID) throws SQLException {
        String sql = "DELETE FROM placeorder WHERE orderID = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, orderID);
            pstmt.executeUpdate();
        }
    }

    // Get place order by orderID
    //public PlaceOrder getPlaceOrderByOrderID(int orderID) throws SQLException {
      //  String sql = "SELECT * FROM placeorder WHERE orderID = ?";
        //try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
          //  pstmt.setInt(1, orderID);
            //ResultSet rs = pstmt.executeQuery();
            //if (rs.next()) {
              //  return new PlaceOrder(
                //    rs.getDate("orderDate").toLocalDate(),
                  //  rs.getInt("orderID"),
                    //rs.getInt("customerID")
                    
               // );
            //} else {
              //  return null;
            //}
        //}
    //}
    
    public PlaceOrder getPlaceOrderByOrderId(int orderId) {
        PlaceOrder placeOrder = null;
        String sql = "SELECT * FROM placeorder WHERE orderID = ?";
    
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, orderId);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    placeOrder = new PlaceOrder(
                        rs.getDate("orderDate").toLocalDate(),// Assuming 'orderdate' is a DATE type
                        rs.getInt("orderID"),// Make sure this column name is correct
                        rs.getInt("idCus")                   // Make sure this column name is correct
                                       
                    );
                } else {
                    System.out.println("No PlaceOrder found for Order ID: " + orderId);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return placeOrder;
    }

}

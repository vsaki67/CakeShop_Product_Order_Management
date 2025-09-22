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
import java.util.ArrayList;
import java.util.List;

public class Order_DAO {
    
    private final Connection conn;
    public Order_DAO(){
                    //calling for dbConnection method in DataBaseConnection class.
        this.conn = DataBase_Connection.dbConnection();
    }
    
    // Add order
    public boolean addOrder(Order order) {
        String sql = "INSERT INTO ordertable (orderID, cakeStatus, amount, userID, payMethod, payStatus) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, order.getOrderID());
            pstmt.setString(2, order.getStatus());
            pstmt.setFloat(3, order.getAmount());
            pstmt.setInt(4, order.getUserID());
            pstmt.setString(5, order.getPaymentMethod());
            pstmt.setString(6, order.getPayStatus());
            pstmt.executeUpdate();
            return true;
        }catch (SQLException e) {
            System.out.println("Error adding order: " + e.getMessage());
            return false;
        }
    }

    //UPDATE order
    public boolean updateOrder(int orderID,  String cakeStatus, float amount, int userID, String payMethod, String payStatus ){
        String sql = "UPDATE ordertable SET cakeStatus = ?, amount = ?, userID = ? ,payMethod = ?, payStatus = ? WHERE orderID = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, cakeStatus);
            pstmt.setFloat(2, amount);
            pstmt.setInt(3, userID);
            pstmt.setString(4, payMethod);
            pstmt.setString(5, payStatus);
            pstmt.setInt(6, orderID);
            pstmt.executeUpdate();
            return true;
        }catch (SQLException e) {
            System.out.println("Error updating order: " + e.getMessage());
            return false;
        }
        
    }
    //DELETE order
    public void deleteOrder(int orderID) {
        String sql = "DELETE FROM ordertable WHERE orderID = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, orderID);
            pstmt.executeUpdate();
        }catch (SQLException e) {
            System.out.println("Error deleting order: " + e.getMessage());
        }
    }

    // GET Order By ID
    public Order getOrderById(int orderID) throws SQLException {
        String sql = "SELECT * FROM ordertable WHERE orderID = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, orderID);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Order(
                    rs.getInt("orderID"),
                    rs.getString("cakeStatus"),
                    rs.getFloat("amount"),
                    rs.getInt("userID"),
                    rs.getString("payMethod"),
                    rs.getString("payStatus")
                );
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new SQLException("Error fetching order: " + e.getMessage());
        }
    }
    
}

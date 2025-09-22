/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Inventory;
import Model.Product;
import java.sql.Connection;

import Model.Report;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class ReportController {
    private final Connection conn;

    public ReportController() {
        this.conn = DataBase_Connection.dbConnection();
    }
    
    private Report getReport() throws SQLException {
    List<Product> products = new ArrayList<>();

    String query = "SELECT idCake, Name, Price, Quantity FROM product";

    try (PreparedStatement pstmt = conn.prepareStatement(query);
         ResultSet rs = pstmt.executeQuery()) {
        while (rs.next()) {
            int cakeId = rs.getInt("idCake");
            String name = rs.getString("Name");
            float price = rs.getFloat("Price");
            int quantity = rs.getInt("Quantity");

            // Create a Product object
            Product product = new Product(cakeId, name, price, quantity); // Adjust constructor if needed
            products.add(product);
        }
    }

    // Now create the Report object with the products list
    return new Report(products, 0.0); // Assuming you want to keep the totalSale as 0.0 for now
}

    
    public double getTotalSalesLast30Days() throws SQLException {
        double totalSales = 0.0;
        LocalDate thirtyDaysAgo = LocalDate.now().minusDays(30);

        // Adjust the query based on your actual table and column names
        String query = "SELECT SUM(o.amount) AS totalAmount " +
                       "FROM ordertable o " +
                       "JOIN placeorder p ON o.orderID = p.orderID " + // Assuming there's a common field
                       "WHERE p.orderDate >= ?";

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setDate(1, java.sql.Date.valueOf(thirtyDaysAgo));
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    totalSales = rs.getDouble("totalAmount");
                }
            }
        }

        return totalSales;
    }


    
 
}

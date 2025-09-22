package Controller;

import Model.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date; //date
import java.time.LocalDate; //local date
import java.util.ArrayList;
import java.util.List;
//import java.util.ArrayList;
//import java.util.List;

public class ProductController {
    private final Connection conn;

    public ProductController() {
        // calling for dbConnection method in DataBaseConnection class.
        this.conn = DataBase_Connection.dbConnection();
    }

    // Add product
    public boolean addProduct(Product product) {
        String sql = "INSERT INTO product (idCake, Name, Description, Price, Category, Quantity, Expiary, userID) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, product.getCakeId());
            pstmt.setString(2, product.getName());
            pstmt.setString(3, product.getDescription());
            pstmt.setFloat(4, product.getPrice());
            pstmt.setString(5, product.getCategory());
            pstmt.setInt(6, product.getQauntity());
            // Convert LocalDate to java.sql.Date
            LocalDate expiaryDate = product.getExpiaryDate(); // Assuming this returns LocalDate
            Date sqlDate = Date.valueOf(expiaryDate); // Convert LocalDate to java.sql.Date
            pstmt.setDate(7, sqlDate);
            
            pstmt.setInt(8, product.getUserID());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error adding product: " + e.getMessage());
            return false;
        }
    }

    // Update product
    public boolean updateProduct(Product product) {
    // Corrected SQL query without the trailing comma
        String sql = "UPDATE Product SET Name = ?, Description = ?, Price = ?, Category = ?, Quantity = ?, Expiary = ?, userID = ? WHERE idCake = ?";
    
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
        // Set parameters
            pstmt.setString(1, product.getName());
            pstmt.setString(2, product.getDescription());
            pstmt.setFloat(3, product.getPrice());
            pstmt.setString(4, product.getCategory());
            pstmt.setInt(5, product.getQauntity());
        
        // Convert LocalDate to java.sql.Date
            LocalDate expiaryDate = product.getExpiaryDate(); // Assuming this returns LocalDate
            Date sqlDate = Date.valueOf(expiaryDate); // Convert LocalDate to java.sql.Date
            pstmt.setDate(6, sqlDate);
        
            pstmt.setInt(7, product.getUserID());
            pstmt.setInt(8, product.getCakeId());
        
        // Execute the update
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0; // Return true if at least one row was updated
        } catch (SQLException e) {
            System.out.println("Error updating product: " + e.getMessage());
            return false;
        }
    }


    // Delete product
    public boolean deleteProduct(int cakeId) {
        String sql = "DELETE FROM Product WHERE idCake = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, cakeId);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error deleting product: " + e.getMessage());
            return false;
        }
    }

    // View products
    public Product getProductById(int id) {
        Product product = null;
        String sql = "SELECT * FROM Product WHERE idCake = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                // Retrieve the expiration date as java.sql.Date
                    Date sqlDate = rs.getDate("Expiary");
                // Convert java.sql.Date to LocalDate
                    LocalDate expiaryDate = (sqlDate != null) ? sqlDate.toLocalDate() : null;
                
                    product = new Product(
                            rs.getInt("idCake"),
                            rs.getString("Name"),
                            rs.getString("Description"),
                            rs.getFloat("Price"),
                            rs.getString("Category"),
                            rs.getInt("Quantity"),
                            expiaryDate,
                            rs.getInt("userID")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving product: " + e.getMessage());
        }
        return product;
    }
// Get all products from the database
    public List<Product> getProducts() {
        List<Product> productList = new ArrayList<>();
        String sql = "SELECT * FROM product";

        try (PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                int idCake = rs.getInt("idCake");
                String name = rs.getString("Name");
                String description = rs.getString("Description");
                float price = rs.getFloat("Price");
                String category = rs.getString("Category");
                int quantity = rs.getInt("Quantity");
            //Date sqlDate = rs.getDate("Expiary"); // Get java.sql.Date
            
            // Convert java.sql.Date to LocalDate
                LocalDate date = rs.getDate("Expiary").toLocalDate();
            
                int userID = rs.getInt("userID");

                Product product = new Product(idCake, name, description, price, category, quantity, date, userID);
                productList.add(product);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving products: " + e.getMessage());
        }
        return productList;
    }
}

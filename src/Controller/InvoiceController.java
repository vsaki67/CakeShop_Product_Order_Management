package Controller;

import Model.Invoice;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import java.time.LocalDate;

public class InvoiceController {
    private final Connection conn;

    public InvoiceController() {
        this.conn = DataBase_Connection.dbConnection();
    }

    // Add invoice to the database
    public void addInvoice(Invoice invoice) {
        String sql = "INSERT INTO invoice (invoiceDate, amount, orderID) VALUES (?, ?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            // Assuming the Invoice model has a method getDate() that returns LocalDate
            LocalDate date = invoice.getDate(); // Call the method on the invoice object
            java.sql.Date sqlDate = java.sql.Date.valueOf(date); // Convert LocalDate to java.sql.Date
            pstmt.setDate(1, sqlDate);
            
            pstmt.setFloat(2, invoice.getTotalAmount());
            pstmt.setInt(3, invoice.getOrderID());
            pstmt.executeUpdate();
            
            System.out.println("Invoice added to database.");
        } catch (SQLException e) {
            System.out.println("Error adding invoice: " + e.getMessage());
        }
    }

    // Get all invoices from the database
    public List<Invoice> getInvoices() {
        List<Invoice> invoiceList = new ArrayList<>();
        String sql = "SELECT * FROM invoice";

        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("invoiceID");
                LocalDate date = rs.getDate("invoiceDate").toLocalDate();
                float totalAmount = rs.getFloat("amount");
                int orderID = rs.getInt("orderID");

                Invoice invoice = new Invoice(id, date, totalAmount, orderID);
                invoiceList.add(invoice);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving invoices: " + e.getMessage());
        }

        return invoiceList;
    }

    // Delete an invoice by ID
    public void deleteInvoice(int invoiceID) {
        String sql = "DELETE FROM invoice WHERE invoiceID = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, invoiceID);
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Invoice deleted.");
            } else {
                System.out.println("Invoice ID not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error deleting invoice: " + e.getMessage());
        }
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;


import Model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class SignUpController {
    private final Connection conn;
    
    public SignUpController(){
                    //calling for dbConnection method in DataBaseConnection class.
        this.conn = DataBase_Connection.dbConnection();
    }
    
    public boolean isUsernameUnique(String username) throws SQLException {
        String query = "SELECT COUNT(*) FROM employeelogin WHERE username = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) == 0; // If count is 0, username is unique
                }
            }
        }
        return false;
    }

    public boolean signUp(String userId, String firstName, String lastName, String designation, String username, String password, String contactNumber) throws SQLException {
        if (!isUsernameUnique(username)) {
            return false; // Username is not unique
        }

        conn.setAutoCommit(false); // Start transaction

        try {
        // Insert into employeeprofile
            String insertEmployeeProfile = "INSERT INTO employeeprofile (userID, firstName, lastName, designation) VALUES (?, ?, ?, ?)";
            try (PreparedStatement profileStmt = conn.prepareStatement(insertEmployeeProfile)) {
                profileStmt.setString(1, userId); // Use provided userId
                profileStmt.setString(2, firstName);
                profileStmt.setString(3, lastName);
                profileStmt.setString(4, designation);

                int affectedRows = profileStmt.executeUpdate();
                if (affectedRows == 0) {
                    throw new SQLException("Inserting employee profile failed, no rows affected.");
                }

            // Insert into employeelogin
                String insertEmployeeLogin = "INSERT INTO employeelogin (userID, userName, password) VALUES (?, ?, ?)";
                try (PreparedStatement loginStmt = conn.prepareStatement(insertEmployeeLogin)) {
                    loginStmt.setString(1, userId); // Use the same userId
                    loginStmt.setString(2, username);
                    loginStmt.setString(3, password);

                    loginStmt.executeUpdate();
                }

            // Insert into employeecontact
                String insertEmployeeContact = "INSERT INTO employeecontact (userID, contact) VALUES (?, ?)";
                try (PreparedStatement contactStmt = conn.prepareStatement(insertEmployeeContact)) {
                    contactStmt.setString(1, userId); // Use the same userId
                    contactStmt.setString(2, contactNumber);

                    contactStmt.executeUpdate();
                }

                conn.commit(); // Commit transaction
                return true; // Sign-up successful

            } catch (SQLException ex) {
                conn.rollback(); // Rollback on failure
                throw ex;
            }
        } finally {
            conn.setAutoCommit(true); // Restore auto-commit mode
        }
    }

    // Method to generate a unique userID (can be customized)
    public String generateUserId() throws SQLException {
        String query = "SELECT COALESCE(MAX(userId), 0) + 1 AS newUserId FROM employeeprofile;";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return String.valueOf(rs.getInt("newUserId")); // Return new userID as a String
                }
            }
        }
        throw new SQLException("Failed to generate new userID.");
    }
    
    
}

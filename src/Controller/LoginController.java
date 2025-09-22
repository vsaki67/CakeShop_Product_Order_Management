package Controller;

import Model.User;
import Model.Cashier;
import Model.Owner;
import Model.Inventory_Manager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {
    private final Connection conn;

    public LoginController() {
        this.conn = DataBase_Connection.dbConnection();  
    }

    public User login(User loginDetails) {
        User loggedInUser = null;
        String query = "SELECT el.username, el.password, ep.designation " +
                        "FROM employeelogin el " +
                        "JOIN employeeprofile ep ON el.userID = ep.userID " +
                        "WHERE el.username = ? AND el.password = ?";

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
        // Set the parameters for the query
            stmt.setString(1, loginDetails.getUserName());
            stmt.setString(2, loginDetails.getPassword());
            ResultSet rs = stmt.executeQuery();

            // If the user exists
            if (rs.next()) {
                // Create the user object with fetched details
                loggedInUser = new User();
                loggedInUser.setUserName(rs.getString("username"));
                loggedInUser.setPassword(rs.getString("password"));
                loggedInUser.setDesignation(rs.getString("designation"));

                // Determine the user's designation and return the appropriate subclass of User
                String designation = rs.getString("designation").toLowerCase();
                switch (designation) {
                    case "cashier":
                        return new Cashier(loggedInUser.getUserName(), loggedInUser.getPassword());
                    case "owner":
                        return new Owner(loggedInUser.getUserName(), loggedInUser.getPassword());
                    case "inventory manager":
                        return new Inventory_Manager(loggedInUser.getUserName(), loggedInUser.getPassword());
                    default:
                        return null; // Unrecognized designation
                }
            } else {
                return null; // User not found
            }

        } catch (SQLException e) {
        e.printStackTrace();
        }

        return null; // Return null if login failed
    }

}

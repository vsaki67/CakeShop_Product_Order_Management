/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;
/**
 *
 * @author asusx
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBase_Connection {
    public static Connection dbConnection(){
        Connection conn = null ;
        try{                                                                //change the cake_shop --> database name
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cake_shop","root","1234"); 
            
            System.out.println("Connected"); 
            return conn;
        }
        catch(SQLException e){
            System.out.println("Connection failed");
            return conn;
        }
    
    } 
}

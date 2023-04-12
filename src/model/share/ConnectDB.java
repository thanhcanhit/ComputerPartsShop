/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.share;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author thanh
 */
public class ConnectDB {
    public static Connection conn = null;
    
    public static void connect() throws SQLException {
        String url = "jdbc:sqlserver://localhost:1433;databasename=ComputerPartsShop";
        String user = "sa";
        String password = "sapassword";
        
        conn = DriverManager.getConnection(url, user, password);
    }
    
    public static void disconnect() {
        try {
            conn.close();
        } catch (Exception e) {
            
        }
    }
}

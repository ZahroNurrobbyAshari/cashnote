/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import java.sql.*;
import java.util.*;

/**
 *
 * @author zahro
 */
public class dbConnection {
    
    // init jdbc parameter
     static final String JDBC_DRIVER = "com.mysql.jdbc.driver";
     static final String DB_URL = "jdbc:mysql://localhost:3306/ta_pbo";
     static final String DB_USER = "root";
     static final String DB_PASSWORD = "";
     
     public static Connection connect(){
         try {
             Class.forName("com.mysql.jdbc.Driver");
             System.out.println("Driver Load");
             Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             System.out.println("Database Connect");
             return connection;
         } catch (Exception e) {
             System.err.println("Connection error : "+e.getMessage());
             return null;
         }
     }
    
     
     
}

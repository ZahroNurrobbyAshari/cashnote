/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package auth;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Window;
import database.dbConnection;
import java.sql.Statement;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



/**
 * FXML Controller class
 *
 * @author zahro
 */
public class LoginController implements Initializable {

    @FXML
    private TextField txt_username;
    @FXML
    private PasswordField txt_password;
    @FXML
    private Button btn_login;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

    @FXML
    public void login(ActionEvent actionEvent) {
       int role;
       Connection conn = dbConnection.connect();
       Statement st;
       ResultSet rs;
       String query = "SELECT*FROM user WHERE username='"+txt_username.getText()+"' AND password='"+txt_password.getText()+"'";
        
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            
            while (rs.next()) {                
                if(rs.getInt("role")==1){
                    Parent root = FXMLLoader.load(getClass().getResource("/bendahara/dashboard.fxml"));
                    Stage window = new Stage();
                    window.setScene(new Scene(root,1280,720));
                    window.setMaximized(false);
                    window.setMaximized(true);
                    window.setResizable(false);
                    window.show();
                }
                else if(rs.getInt("role")==2){
                     Parent root = FXMLLoader.load(getClass().getResource("/waliKelas/finance.fxml"));
                    Stage window = new Stage();
                    
                    window.setScene(new Scene(root,1280,720));
                    window.setMaximized(false);
                    window.setMaximized(true);
                    window.setResizable(false);
                    window.show();
                           
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
       
    }

}

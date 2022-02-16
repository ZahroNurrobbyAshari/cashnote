/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package adminController;

import database.dbConnection;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.Dashboard;
import org.kordamp.ikonli.javafx.FontIcon;

/**
 * FXML Controller class
 *
 * @author zahro
 */
public class DashboardController implements Initializable {

    @FXML
    Button btn_income;
    @FXML
    Button btn_outcome;
    @FXML
    Button btn_report;
    @FXML
    Label lbl_sum_income;
    @FXML
    Label lbl_balance;
    @FXML
    Label lbl_sum_outcome;
    @FXML
    FontIcon icon;
    /**
     * Initializes the controller class.
     */
    
    public void sumTod(){
        Connection conn = dbConnection.connect();
        Statement st;
        ResultSet rs;
       
        String query = "SELECT SUM(income.nominal) as sumIncome, (sum(income.nominal) - sum(outcome.nominal)) as balance ,SUM(outcome.nominal) as sumOutcome  FROM income JOIN outcome";
        
        try {
            
            st = conn.createStatement();
            rs = st.executeQuery(query);
            while (rs.next()) {                
                
                lbl_sum_income.setText(rs.getString("sumIncome"));
                lbl_sum_outcome.setText(rs.getString("sumOutcome"));
                lbl_balance.setText(rs.getString("balance"));
                
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        sumTod();
        
    }    
    
     public void switchToIncome() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/bendahara/income.fxml"));
        Stage window = (Stage)btn_income.getScene().getWindow();
        window.setScene(new Scene(root,1280,720));
        window.setMaximized(false);
        window.setMaximized(true);
        window.setResizable(false);
       
    }
    
   
    public void switchToOutcome() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/bendahara/outcome.fxml"));
        Stage window = (Stage)btn_outcome.getScene().getWindow();
        window.setScene(new Scene(root,1280,720));
        window.setMaximized(false);
        window.setMaximized(true);
        window.setResizable(false);
    }
    

    public void switchToReport() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/bendahara/report.fxml"));
        Stage window = (Stage)btn_report.getScene().getWindow();
        window.setScene(new Scene(root,1280,720));
        window.setMaximized(false);
        window.setMaximized(true);
        window.setResizable(false);
    }
    
    
}

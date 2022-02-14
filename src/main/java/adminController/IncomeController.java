/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package adminController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author zahro
 */
public class IncomeController implements Initializable {

    @FXML
    Button btn_dashboard;
    @FXML
    Button btn_outcome;
    @FXML
    Button btn_report;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
    public void switchToDashboard() throws IOException {
       Parent root = FXMLLoader.load(getClass().getResource("/bendahara/dashboard.fxml"));
        Stage window = (Stage)btn_dashboard.getScene().getWindow();
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
